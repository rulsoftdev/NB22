package org.rulsoft.ap.nb22.domain.usuario.usecase

import arrow.core.Either
import org.koin.core.annotation.Factory
import org.rulsoft.ap.nb22.domain.carta.CartaLocalRepository
import org.rulsoft.ap.nb22.domain.carta.CartaRemoteRepository
import org.rulsoft.ap.nb22.domain.logger.Logger
import org.rulsoft.ap.nb22.domain.usuario.UsuarioLocalRepository
import org.rulsoft.ap.nb22.domain.usuario.UsuarioRemoteRepository

@Factory
class UserCheckAndLoadCartasUseCase(
    private val usuarioLocalRepository: UsuarioLocalRepository,
    private val cartaLocalRepository: CartaLocalRepository,
    private val usuarioRemoteRepository: UsuarioRemoteRepository,
    private val cartaRemoteRepository: CartaRemoteRepository,
    // private val logger: Logger
) {

    sealed class Result {
        object Success : Result()
        object UserNotFound : Result()
        object CartaNotFound : Result()
        data class Error(val message: String) : Result()
    }

    suspend operator fun invoke(email: String, recordarEmail: Boolean = false): Result {
        // Paso 1: Comprobar si el usuario existe en el servidor remoto
        val usuarioResponse = usuarioRemoteRepository.findUsuarioByEmail(email)
        if (usuarioResponse is Either.Left) {
            //logger.d("UserCheckAndLoadCartasUseCase", "Usuario no encontrado: $email")
            return Result.UserNotFound
        }

        val usuario = (usuarioResponse as Either.Right).value

        // Paso 2: Guardar el usuario en la base de datos local
        if (recordarEmail) {
            val newUsuario = usuario.copy(recordarEmail = true)
            usuarioLocalRepository.insertOrUpdateUser(newUsuario)
            //logger.d("UserCheckAndLoadCartasUseCase", "Hemos insertado el usuario: $newUsuario en la base de datos local")
        }

        // Paso 3: Comprobar si la carta existe en el servidor local
        val cartas = cartaLocalRepository.fetchCartasPropias(usuario.id)
        if (!cartas.isNullOrEmpty()) {
            // Tenemos cartas en la base de datos local, no es necesario hacer nada
            //logger.d("UserCheckAndLoadCartasUseCase", "Carta encontrada en Room: $cartas")
            return Result.Success
        }

        // Paso 4: Si no hay cartas en la base de datos local, obtenerlas del servidor remoto
        val cartasResponse = cartaRemoteRepository.findCartasByEmail(email)
        if (cartasResponse is Either.Left) {
            //logger.d("UserCheckAndLoadCartasUseCase", "Cartas no encontradas")
            return Result.CartaNotFound
        }

        val cartasRemote = (cartasResponse as Either.Right).value
        //logger.d("UserCheckAndLoadCartasUseCase", "Carta encontrada: $cartasRemote")
        if (cartasRemote.isEmpty()) {
            //logger.d("UserCheckAndLoadCartasUseCase", "No se encontraron cartas en el servidor remoto")
            return Result.CartaNotFound
        }
        cartasRemote.forEach{
            // Paso 5: Guardar cada carta en la base de datos local
            cartaLocalRepository.insertOrUpdateCarta(it)
            //logger.d("UserCheckAndLoadCartasUseCase", "Carta guardada: $it")
        }
        return Result.Success
    }
}
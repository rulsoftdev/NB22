package dev.rulsoft.nb22.data.usuario.networking

import arrow.core.Either
import dev.rulsoft.nb22.data.common.networking.BaseRemoteDataRepository
import dev.rulsoft.nb22.core.ApiError
import dev.rulsoft.nb22.domain.usuario.model.Usuario
import dev.rulsoft.nb22.domain.usuario.UsuarioRemoteRepository
import dev.rulsoft.nb22.data.usuario.mappers.toUsuario
import dev.rulsoft.nb22.data.usuario.networking.dto.FiltroUsuarioDto

class UsuarioRemoteDataRepository(private val usuarioServiceKtor: UsuarioServiceKtor):
    BaseRemoteDataRepository(), UsuarioRemoteRepository {

    override suspend fun findUsuarioByEmail(email: String): Either<ApiError, Usuario> {
        val response = usuarioServiceKtor.findUsuarioByEmail(FiltroUsuarioDto(email))
        return handleResponse(response) { usuarioDto ->
            usuarioDto.toUsuario()
        }
    }

}
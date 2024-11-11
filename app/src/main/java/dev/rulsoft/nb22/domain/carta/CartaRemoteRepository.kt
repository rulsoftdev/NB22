package dev.rulsoft.nb22.domain.carta

import arrow.core.Either
import dev.rulsoft.nb22.common.domain.ApiError
import dev.rulsoft.nb22.domain.carta.model.Carta
import dev.rulsoft.nb22.domain.carta.model.CartaSimple

interface CartaRemoteRepository {

    suspend fun fetchCartas(): Either<ApiError, List<Carta>>
    suspend fun fetchCartaById(id: Int): Either<ApiError, List<Carta>>
    suspend fun findCartasByCodigo(codigo: String): Either<ApiError, List<Carta>>
    suspend fun findCartasByEmail(email: String): Either<ApiError, List<Carta>>
    suspend fun findCartasSimplesByEmail(email: String): Either<ApiError, List<CartaSimple>>
    suspend fun findCartaDtosRelacion(ids: String): Either<ApiError, List<Carta>>
    suspend fun updateCarta(id: Int, fijada: Boolean): Either<ApiError, Carta>
}

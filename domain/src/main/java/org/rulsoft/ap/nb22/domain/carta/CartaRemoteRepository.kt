package org.rulsoft.ap.nb22.domain.carta

import arrow.core.Either
import org.rulsoft.ap.nb22.core.ApiError
import org.rulsoft.ap.nb22.domain.carta.model.Carta
import org.rulsoft.ap.nb22.domain.carta.model.CartaSimple

interface CartaRemoteRepository {

    suspend fun fetchCartas(): Either<ApiError, List<Carta>>
    suspend fun fetchCartaById(id: Int): Either<ApiError, List<Carta>>
    suspend fun findCartasByCodigo(codigo: String): Either<ApiError, List<Carta>>
    suspend fun findCartasByEmail(email: String): Either<ApiError, List<Carta>>
    suspend fun findCartasSimplesByEmail(email: String): Either<ApiError, List<CartaSimple>>
    suspend fun findCartaDtosRelacion(ids: String): Either<ApiError, List<Carta>>
    suspend fun updateCarta(id: Int, fijada: Boolean): Either<ApiError, Carta>
}

package dev.rulsoft.nb22.domain.numero

import arrow.core.Either
import dev.rulsoft.nb22.common.domain.ApiError
import dev.rulsoft.nb22.domain.numero.model.Numero
import dev.rulsoft.nb22.domain.numero.model.NumeroFree

interface NumeroRemoteRepository {

    suspend fun fetchNumeroById(id: Int): Either<ApiError, Numero>
    suspend fun fetchNumeros(): Either<ApiError, List<Numero>>
    suspend fun fetchResumenFreeById(id: Int): Either<ApiError, NumeroFree>

}

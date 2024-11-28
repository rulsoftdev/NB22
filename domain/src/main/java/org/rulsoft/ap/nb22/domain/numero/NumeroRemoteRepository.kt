package org.rulsoft.ap.nb22.domain.numero

import arrow.core.Either
import org.rulsoft.ap.nb22.core.ApiError
import org.rulsoft.ap.nb22.domain.numero.model.Numero
import org.rulsoft.ap.nb22.domain.numero.model.NumeroFree

interface NumeroRemoteRepository {

    suspend fun fetchNumeroById(id: Int): Either<ApiError, Numero>
    suspend fun fetchNumeros(): Either<ApiError, List<Numero>>
    suspend fun fetchResumenFreeById(id: Int): Either<ApiError, NumeroFree>

}

package org.rulsoft.ap.nb22.domain.terapeuta

import arrow.core.Either
import org.rulsoft.ap.nb22.core.ApiError
import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapeuta

interface TerapeutaRepository {

    suspend fun fetchTerapeutaById(id: Int): Either<ApiError, Terapeuta>
    suspend fun fetchTerapeutas(): Either<ApiError, List<Terapeuta>>

}

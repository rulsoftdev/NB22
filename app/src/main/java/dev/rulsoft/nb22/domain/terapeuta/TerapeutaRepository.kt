package dev.rulsoft.nb22.domain.terapeuta

import arrow.core.Either
import dev.rulsoft.nb22.common.domain.ApiError
import dev.rulsoft.nb22.domain.terapeuta.model.Terapeuta

interface TerapeutaRepository {

    suspend fun fetchTerapeutaById(id: Int): Either<ApiError, Terapeuta>
    suspend fun fetchTerapeutas(): Either<ApiError, List<Terapeuta>>

}

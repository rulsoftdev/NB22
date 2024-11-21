package dev.rulsoft.nb22.domain.curso

import arrow.core.Either
import dev.rulsoft.nb22.core.ApiError
import dev.rulsoft.nb22.domain.curso.model.Curso

interface CursoRemoteRepository {

    suspend fun fetchCursos(): Either<ApiError, List<Curso>>
    suspend fun fetchCursoById(id: Int): Either<ApiError, Curso>

}

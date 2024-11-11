package dev.rulsoft.nb22.data.curso.networking

import arrow.core.Either
import dev.rulsoft.nb22.common.data.networking.BaseRemoteDataRepository
import dev.rulsoft.nb22.common.domain.ApiError
import dev.rulsoft.nb22.data.curso.mappers.toCurso
import dev.rulsoft.nb22.domain.curso.model.Curso
import dev.rulsoft.nb22.domain.curso.CursoRemoteRepository

class CursoRemoteDataRemoteRepository(private val cursoServiceKtor: CursoServiceKtor):
    BaseRemoteDataRepository(), CursoRemoteRepository {

    override suspend fun fetchCursos(): Either<ApiError, List<Curso>> {
        val response = cursoServiceKtor.fetchCursos()
        return handleResponse(response) { cursoDtoList ->
            cursoDtoList.map { it.toCurso() }
        }
    }

    override suspend fun fetchCursoById(id: Int): Either<ApiError, Curso> {
        val response = cursoServiceKtor.fetchCursoById(id)
        return handleResponse(response) { cursoDto ->
            cursoDto.toCurso()
        }
    }

}
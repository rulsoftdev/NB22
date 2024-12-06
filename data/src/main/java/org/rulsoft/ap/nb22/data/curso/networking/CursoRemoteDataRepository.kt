package org.rulsoft.ap.nb22.data.curso.networking

import arrow.core.Either
import org.rulsoft.ap.nb22.data.common.networking.BaseRemoteDataRepository
import org.rulsoft.ap.nb22.data.curso.mappers.toCurso
import org.rulsoft.ap.nb22.domain.curso.model.Curso
import org.rulsoft.ap.nb22.domain.curso.CursoRemoteRepository

class CursoRemoteDataRepository(private val cursoServiceKtor: CursoServiceKtor):
    BaseRemoteDataRepository(), CursoRemoteRepository {

    override suspend fun fetchCursos(): Either<org.rulsoft.ap.nb22.core.ApiError, List<Curso>> {
        val response = cursoServiceKtor.fetchCursos()
        return handleResponse(response) { cursoDtoList ->
            cursoDtoList.map { it.toCurso() }
        }
    }

    override suspend fun fetchCursoById(id: Int): Either<org.rulsoft.ap.nb22.core.ApiError, Curso> {
        val response = cursoServiceKtor.fetchCursoById(id)
        return handleResponse(response) { cursoDto ->
            cursoDto.toCurso()
        }
    }

}
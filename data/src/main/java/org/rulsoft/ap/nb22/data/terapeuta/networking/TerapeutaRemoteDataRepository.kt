package org.rulsoft.ap.nb22.data.terapeuta.networking

import arrow.core.Either
import org.rulsoft.ap.nb22.data.common.networking.BaseRemoteDataRepository
import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapeuta
import org.rulsoft.ap.nb22.data.terapeuta.mappers.toTerapeuta
import org.rulsoft.ap.nb22.domain.terapeuta.TerapeutaRepository

class TerapeutaRemoteDataRepository(private val terapeutaServiceKtor: TerapeutaServiceKtor):
    BaseRemoteDataRepository(), TerapeutaRepository {

    override suspend fun fetchTerapeutas(): Either<org.rulsoft.ap.nb22.core.ApiError, List<Terapeuta>> {
        val response = terapeutaServiceKtor.fetchTerapeutas()
        return handleResponse(response) { terapeutaDtoList ->
            terapeutaDtoList.map { it.toTerapeuta() }
        }
    }

    override suspend fun fetchTerapeutaById(id: Int): Either<org.rulsoft.ap.nb22.core.ApiError, Terapeuta>{
        val response = terapeutaServiceKtor.fetchTerapeutaById(id)
        return handleResponse(response) { terapeutaDto ->
            terapeutaDto.toTerapeuta()
        }
    }

}
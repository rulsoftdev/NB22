package dev.rulsoft.nb22.data.terapeuta.networking

import arrow.core.Either
import dev.rulsoft.nb22.data.common.networking.BaseRemoteDataRepository
import dev.rulsoft.nb22.core.ApiError
import dev.rulsoft.nb22.domain.terapeuta.model.Terapeuta
import dev.rulsoft.nb22.data.terapeuta.mappers.toTerapeuta
import dev.rulsoft.nb22.domain.terapeuta.TerapeutaRepository

class TerapeutaRemoteDataRepository(private val terapeutaServiceKtor: TerapeutaServiceKtor):
    BaseRemoteDataRepository(), TerapeutaRepository {

    override suspend fun fetchTerapeutas(): Either<ApiError, List<Terapeuta>> {
        val response = terapeutaServiceKtor.fetchTerapeutas()
        return handleResponse(response) { terapeutaDtoList ->
            terapeutaDtoList.map { it.toTerapeuta() }
        }
    }

    override suspend fun fetchTerapeutaById(id: Int): Either<ApiError, Terapeuta>{
        val response = terapeutaServiceKtor.fetchTerapeutaById(id)
        return handleResponse(response) { terapeutaDto ->
            terapeutaDto.toTerapeuta()
        }
    }

}
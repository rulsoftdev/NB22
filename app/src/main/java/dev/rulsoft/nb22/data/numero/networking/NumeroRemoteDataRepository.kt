package dev.rulsoft.nb22.data.numero.networking

import arrow.core.Either
import dev.rulsoft.nb22.data.common.networking.BaseRemoteDataRepository
import dev.rulsoft.nb22.core.ApiError
import dev.rulsoft.nb22.data.numero.mappers.toNumero
import dev.rulsoft.nb22.data.numero.mappers.toNumeroFree
import dev.rulsoft.nb22.domain.numero.model.Numero
import dev.rulsoft.nb22.domain.numero.model.NumeroFree
import dev.rulsoft.nb22.domain.numero.NumeroRemoteRepository


class NumeroRemoteDataRepository(private val numeroServiceKtor: NumeroServiceKtor):
    BaseRemoteDataRepository(), NumeroRemoteRepository {

    override suspend fun fetchNumeroById(id: Int): Either<ApiError, Numero> {
        val response = numeroServiceKtor.fetchNumeroById(id)
        return handleResponse(response) { numeroDto ->
            numeroDto.toNumero()
        }
    }

    override suspend fun fetchNumeros(): Either<ApiError, List<Numero>> {
        val response = numeroServiceKtor.fetchNumeros()
        return handleResponse(response) { numeroDtoList ->
            numeroDtoList.map { it.toNumero() }
        }
    }

    override suspend fun fetchResumenFreeById(id: Int): Either<ApiError, NumeroFree> {
        val response = numeroServiceKtor.fetchResumenFreeById(id)
        return handleResponse(response) { numeroFreeDto ->
            numeroFreeDto.toNumeroFree()
        }
    }

}
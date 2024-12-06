package org.rulsoft.ap.nb22.data.numero.networking

import arrow.core.Either
import org.rulsoft.ap.nb22.data.common.networking.BaseRemoteDataRepository
import org.rulsoft.ap.nb22.data.numero.mappers.toNumero
import org.rulsoft.ap.nb22.data.numero.mappers.toNumeroFree
import org.rulsoft.ap.nb22.domain.numero.model.Numero
import org.rulsoft.ap.nb22.domain.numero.model.NumeroFree
import org.rulsoft.ap.nb22.domain.numero.NumeroRemoteRepository


class NumeroRemoteDataRepository(private val numeroServiceKtor: NumeroServiceKtor):
    BaseRemoteDataRepository(), NumeroRemoteRepository {

    override suspend fun fetchNumeroById(id: Int): Either<org.rulsoft.ap.nb22.core.ApiError, Numero> {
        val response = numeroServiceKtor.fetchNumeroById(id)
        return handleResponse(response) { numeroDto ->
            numeroDto.toNumero()
        }
    }

    override suspend fun fetchNumeros(): Either<org.rulsoft.ap.nb22.core.ApiError, List<Numero>> {
        val response = numeroServiceKtor.fetchNumeros()
        return handleResponse(response) { numeroDtoList ->
            numeroDtoList.map { it.toNumero() }
        }
    }

    override suspend fun fetchResumenFreeById(id: Int): Either<org.rulsoft.ap.nb22.core.ApiError, NumeroFree> {
        val response = numeroServiceKtor.fetchResumenFreeById(id)
        return handleResponse(response) { numeroFreeDto ->
            numeroFreeDto.toNumeroFree()
        }
    }

}
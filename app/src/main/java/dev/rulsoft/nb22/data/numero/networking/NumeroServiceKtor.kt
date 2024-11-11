package dev.rulsoft.nb22.data.numero.networking

import dev.rulsoft.nb22.common.data.networking.BaseServiceKtor
import dev.rulsoft.nb22.common.data.networking.Response
import dev.rulsoft.nb22.common.data.util.ApiConstants
import dev.rulsoft.nb22.data.numero.networking.dto.NumeroDto
import dev.rulsoft.nb22.data.numero.networking.dto.NumeroFreeDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class NumeroServiceKtor(httpClient: HttpClient): BaseServiceKtor(httpClient) {

    suspend fun fetchNumeroById(id: Int): Response<NumeroDto> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_NUMERO}/$id") }
        }
    }

    suspend fun fetchNumeros(): Response<List<NumeroDto>> {
        return executeRequest {
            httpClient.get { url(ApiConstants.END_POINTS_GET_NUMEROS) }
        }

    }

    suspend fun fetchResumenFreeById(id: Int): Response<NumeroFreeDto> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_NUMERO_PP_FREE}/$id") }
        }
    }
}
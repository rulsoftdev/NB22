package dev.rulsoft.nb22.data.terapeuta.networking

import dev.rulsoft.nb22.common.data.networking.BaseServiceKtor
import dev.rulsoft.nb22.common.data.networking.Response
import dev.rulsoft.nb22.common.data.util.ApiConstants
import dev.rulsoft.nb22.data.terapeuta.networking.dto.TerapeutaDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class TerapeutaServiceKtor (httpClient: HttpClient): BaseServiceKtor(httpClient) {

     suspend fun fetchTerapeutaById(id: Int): Response<TerapeutaDto> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_TERAPEUTA}/$id") }
        }
    }

    suspend fun fetchTerapeutas(): Response<List<TerapeutaDto>> {
        return executeRequest {
            httpClient.get { url(ApiConstants.END_POINTS_GET_TERAPEUTAS) }
        }
    }

}
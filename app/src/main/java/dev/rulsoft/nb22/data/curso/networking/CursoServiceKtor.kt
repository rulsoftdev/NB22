package dev.rulsoft.nb22.data.curso.networking

import dev.rulsoft.nb22.common.data.networking.BaseServiceKtor
import dev.rulsoft.nb22.common.data.networking.Response
import dev.rulsoft.nb22.common.data.util.ApiConstants
import dev.rulsoft.nb22.data.curso.networking.dto.CursoDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class CursoServiceKtor(httpClient: HttpClient): BaseServiceKtor(httpClient) {

    suspend fun fetchCursos(): Response<List<CursoDto>> {
        return executeRequest {
            httpClient.get { url(ApiConstants.END_POINTS_GET_CURSOS) }
        }
    }

    suspend fun fetchCursoById(id: Int): Response<CursoDto> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_CURSO}/$id") }
        }
    }

}
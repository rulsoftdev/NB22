package org.rulsoft.ap.nb22.data.curso.networking

import org.rulsoft.ap.nb22.data.common.networking.ApiClientKtor
import org.rulsoft.ap.nb22.data.common.networking.BaseServiceKtor
import org.rulsoft.ap.nb22.data.common.networking.Response
import org.rulsoft.ap.nb22.data.common.util.ApiConstants
import org.rulsoft.ap.nb22.data.curso.networking.dto.CursoDto

class CursoServiceKtor(apiClient: ApiClientKtor): BaseServiceKtor(apiClient) {

    suspend fun fetchCursos(): Response<List<CursoDto>> {
        return apiClient.get(ApiConstants.END_POINTS_GET_CURSOS)
    }

    suspend fun fetchCursoById(id: Int): Response<CursoDto> {
        return apiClient.get("${ApiConstants.END_POINTS_GET_CURSO}/$id")
    }

}
package org.rulsoft.ap.nb22.data.terapeuta.networking

import org.rulsoft.ap.nb22.data.common.networking.BaseServiceKtor
import org.rulsoft.ap.nb22.data.common.networking.Response
import org.rulsoft.ap.nb22.data.common.util.ApiConstants
import org.rulsoft.ap.nb22.data.terapeuta.networking.dto.TerapeutaDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class TerapeutaServiceKtor (): BaseServiceKtor() {

    suspend fun fetchTerapeutaById(id: Int): Response<TerapeutaDto> {
        return get("${ApiConstants.END_POINTS_GET_TERAPEUTA}/$id")
    }

    suspend fun fetchTerapeutas(): Response<List<TerapeutaDto>> {
        return get(ApiConstants.END_POINTS_GET_TERAPEUTAS)
    }

}
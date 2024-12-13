package dev.rulsoft.nb22.data.numero.networking

import dev.rulsoft.nb22.data.common.networking.ApiClientKtor
import dev.rulsoft.nb22.data.common.networking.BaseServiceKtor
import dev.rulsoft.nb22.data.common.networking.Response
import dev.rulsoft.nb22.data.common.util.ApiConstants
import dev.rulsoft.nb22.data.numero.networking.dto.NumeroDto
import dev.rulsoft.nb22.data.numero.networking.dto.NumeroFreeDto

class NumeroServiceKtor(apiClient: ApiClientKtor): BaseServiceKtor(apiClient) {

    suspend fun fetchNumeroById(id: Int): Response<NumeroDto> {
        return apiClient.get ("${ApiConstants.END_POINTS_GET_NUMERO}/$id")
    }

    suspend fun fetchNumeros(): Response<List<NumeroDto>> {
        return apiClient.get(ApiConstants.END_POINTS_GET_NUMEROS)
    }

    suspend fun fetchResumenFreeById(id: Int): Response<NumeroFreeDto> {
        return apiClient.get("${ApiConstants.END_POINTS_GET_NUMERO_PP_FREE}/$id")
    }
}
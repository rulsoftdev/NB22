package org.rulsoft.ap.nb22.data.numero.networking

import org.rulsoft.ap.nb22.data.common.networking.BaseServiceKtor
import org.rulsoft.ap.nb22.data.common.networking.Response
import org.rulsoft.ap.nb22.data.common.util.ApiConstants
import org.rulsoft.ap.nb22.data.numero.networking.dto.NumeroDto
import org.rulsoft.ap.nb22.data.numero.networking.dto.NumeroFreeDto

class NumeroServiceKtor(): BaseServiceKtor() {

    suspend fun fetchNumeroById(id: Int): Response<NumeroDto> {
        return get ("${ApiConstants.END_POINTS_GET_NUMERO}/$id")
    }

    suspend fun fetchNumeros(): Response<List<NumeroDto>> {
        return get(ApiConstants.END_POINTS_GET_NUMEROS)
    }

    suspend fun fetchResumenFreeById(id: Int): Response<NumeroFreeDto> {
        return get("${ApiConstants.END_POINTS_GET_NUMERO_PP_FREE}/$id")
    }
}
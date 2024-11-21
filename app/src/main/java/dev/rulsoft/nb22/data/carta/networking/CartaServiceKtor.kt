package dev.rulsoft.nb22.data.carta.networking

import dev.rulsoft.nb22.data.carta.networking.dto.CartaDto
import dev.rulsoft.nb22.data.carta.networking.dto.CartaSimpleDto
import dev.rulsoft.nb22.data.carta.networking.dto.CartaUpdateDto
import dev.rulsoft.nb22.data.carta.networking.dto.FiltrosCartasDto
import dev.rulsoft.nb22.data.common.networking.ApiClientKtor
import dev.rulsoft.nb22.data.common.networking.BaseServiceKtor
import dev.rulsoft.nb22.data.common.networking.Response
import dev.rulsoft.nb22.data.common.util.ApiConstants

class CartaServiceKtor(apiClient: ApiClientKtor): BaseServiceKtor(apiClient) {

    suspend fun fetchCartas(): Response<List<CartaDto>> {
        return apiClient.get(ApiConstants.END_POINTS_GET_CARTAS)
    }

    suspend fun fetchCartasById(id: Int): Response<List<CartaDto>> {
        return apiClient.get("${ApiConstants.END_POINTS_GET_CARTAS_BY_ID}/$id")
    }

    suspend fun findCartasByCodigo(codigo: String): Response<List<CartaDto>> {
        return apiClient.get("${ApiConstants.END_POINTS_GET_CARTAS_BY_CODIGO}/$codigo")
    }

    suspend fun findCartasByEmail(filtrosCartasDto: FiltrosCartasDto): Response<List<CartaDto>> {
        return apiClient.post(ApiConstants.END_POINTS_POST_CARTAS_BY_EMAIL, filtrosCartasDto)
    }

    suspend fun findCartasSimplesByEmail(filtrosCartasDto: FiltrosCartasDto): Response<List<CartaSimpleDto>> {
        return apiClient.post(ApiConstants.END_POINTS_POST_CARTAS_SIMPLES_BY_EMAIL, filtrosCartasDto)
    }

    suspend fun findCartaDtosRelacion(ids: String): Response<List<CartaDto>> {
        return apiClient.get("${ApiConstants.END_POINTS_GET_CARTAS_RELACION}?ids=$ids")
    }

    suspend fun updateCartaFijada(carta: CartaUpdateDto): Response<CartaDto> {
        return apiClient.put(ApiConstants.END_POINTS_PUT_CARTA, carta)
    }

}


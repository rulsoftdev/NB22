package dev.rulsoft.nb22.data.carta.networking

import dev.rulsoft.nb22.common.data.networking.BaseServiceKtor
import dev.rulsoft.nb22.common.data.networking.Response
import dev.rulsoft.nb22.common.data.util.ApiConstants
import dev.rulsoft.nb22.data.carta.networking.dto.CartaDto
import dev.rulsoft.nb22.data.carta.networking.dto.CartaSimpleDto
import dev.rulsoft.nb22.data.carta.networking.dto.CartaUpdateDto
import dev.rulsoft.nb22.data.carta.networking.dto.FiltrosCartasDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CartaServiceKtor(httpClient: HttpClient): BaseServiceKtor(httpClient) {

    suspend fun fetchCartas(): Response<List<CartaDto>> {
        return executeRequest {
            httpClient.get { url(ApiConstants.END_POINTS_GET_CARTAS) }
        }
    }

    suspend fun fetchCartasById(id: Int): Response<List<CartaDto>> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_CARTAS_BY_ID}/$id") }
        }
    }

    suspend fun findCartasByCodigo(codigo: String): Response<List<CartaDto>> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_CARTAS_BY_CODIGO}/$codigo") }
        }
    }

    suspend fun findCartasByEmail(filtrosCartasDto: FiltrosCartasDto): Response<List<CartaDto>> {
        return executeRequest {
            httpClient.post {
                url(ApiConstants.END_POINTS_POST_CARTAS_BY_EMAIL)
                setBody(filtrosCartasDto)
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun findCartasSimplesByEmail(filtrosCartasDto: FiltrosCartasDto): Response<List<CartaSimpleDto>> {
        return executeRequest {
            httpClient.post {
                url(ApiConstants.END_POINTS_POST_CARTAS_SIMPLES_BY_EMAIL)
                setBody(filtrosCartasDto)
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun findCartaDtosRelacion(ids: String): Response<List<CartaDto>> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_CARTAS_RELACION}?ids=$ids") }
        }
    }

    suspend fun updateCartaFijada(carta: CartaUpdateDto): Response<CartaDto> {
        return executeRequest {
            httpClient.put {
                url(ApiConstants.END_POINTS_PUT_CARTA)
                setBody(carta)
                contentType(ContentType.Application.Json)
            }
        }
    }

}


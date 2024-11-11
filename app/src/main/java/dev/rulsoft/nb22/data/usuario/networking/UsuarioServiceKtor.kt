package dev.rulsoft.nb22.data.usuario.networking

import dev.rulsoft.nb22.common.data.networking.BaseServiceKtor
import dev.rulsoft.nb22.common.data.networking.Response
import dev.rulsoft.nb22.common.data.util.ApiConstants
import dev.rulsoft.nb22.data.carta.networking.dto.CartaSimpleDto
import dev.rulsoft.nb22.data.carta.networking.dto.FiltrosCartasDto
import dev.rulsoft.nb22.data.usuario.networking.dto.FiltroUsuarioDto
import dev.rulsoft.nb22.data.usuario.networking.dto.UsuarioDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UsuarioServiceKtor (httpClient: HttpClient): BaseServiceKtor(httpClient) {

    /*suspend fun fetchUsuarioById(id: Int): Response<UsuarioDto> {
        return executeRequest {
            httpClient.get { url("${ApiConstants.END_POINTS_GET_USUARIO}/$id") }
        }
    }*/

    suspend fun findUsuarioByEmail(filtroUsuarioDto: FiltroUsuarioDto): Response<UsuarioDto> {
        return executeRequest {
            httpClient.post {
                url(ApiConstants.END_POINTS_GET_USUARIO_BY_EMAIL)
                setBody(filtroUsuarioDto)
                contentType(ContentType.Application.Json)
            }
        }
    }

}

package dev.rulsoft.nb22.data.usuario.networking

import dev.rulsoft.nb22.data.common.networking.ApiClientKtor
import dev.rulsoft.nb22.data.common.networking.BaseServiceKtor
import dev.rulsoft.nb22.data.common.networking.Response
import dev.rulsoft.nb22.data.common.util.ApiConstants
import dev.rulsoft.nb22.data.usuario.networking.dto.FiltroUsuarioDto
import dev.rulsoft.nb22.data.usuario.networking.dto.UsuarioDto

class UsuarioServiceKtor (apiClient: ApiClientKtor): BaseServiceKtor(apiClient) {

    /*suspend fun fetchUsuarioById(id: Int): Response<UsuarioDto> {
        return apiclient.get("${ApiConstants.END_POINTS_GET_USUARIO}/$id")
    }*/

    suspend fun findUsuarioByEmail(filtroUsuarioDto: FiltroUsuarioDto): Response<UsuarioDto> {
        return apiClient.post (ApiConstants.END_POINTS_GET_USUARIO_BY_EMAIL, filtroUsuarioDto)
    }

}

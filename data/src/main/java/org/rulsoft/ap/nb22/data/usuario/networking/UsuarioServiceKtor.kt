package org.rulsoft.ap.nb22.data.usuario.networking

import org.rulsoft.ap.nb22.data.common.networking.BaseServiceKtor
import org.rulsoft.ap.nb22.data.common.networking.Response
import org.rulsoft.ap.nb22.data.common.util.ApiConstants
import org.rulsoft.ap.nb22.data.usuario.networking.dto.FiltroUsuarioDto
import org.rulsoft.ap.nb22.data.usuario.networking.dto.UsuarioDto

class UsuarioServiceKtor (): BaseServiceKtor() {

    /*suspend fun fetchUsuarioById(id: Int): Response<UsuarioDto> {
        return apiclient.get("${ApiConstants.END_POINTS_GET_USUARIO}/$id")
    }*/

    suspend fun findUsuarioByEmail(filtroUsuarioDto: FiltroUsuarioDto): Response<UsuarioDto> {
        return post (ApiConstants.END_POINTS_GET_USUARIO_BY_EMAIL, filtroUsuarioDto)
    }

}

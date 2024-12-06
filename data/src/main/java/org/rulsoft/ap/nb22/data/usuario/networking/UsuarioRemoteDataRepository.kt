package org.rulsoft.ap.nb22.data.usuario.networking

import arrow.core.Either
import org.rulsoft.ap.nb22.data.common.networking.BaseRemoteDataRepository
import org.rulsoft.ap.nb22.domain.usuario.model.Usuario
import org.rulsoft.ap.nb22.domain.usuario.UsuarioRemoteRepository
import org.rulsoft.ap.nb22.data.usuario.mappers.toUsuario
import org.rulsoft.ap.nb22.data.usuario.networking.dto.FiltroUsuarioDto

class UsuarioRemoteDataRepository(private val usuarioServiceKtor: UsuarioServiceKtor):
    BaseRemoteDataRepository(), UsuarioRemoteRepository {

    override suspend fun findUsuarioByEmail(email: String): Either<org.rulsoft.ap.nb22.core.ApiError, Usuario> {
        val response = usuarioServiceKtor.findUsuarioByEmail(FiltroUsuarioDto(email))
        return handleResponse(response) { usuarioDto ->
            usuarioDto.toUsuario()
        }
    }

}
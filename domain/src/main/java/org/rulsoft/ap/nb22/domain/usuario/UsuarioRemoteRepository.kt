package org.rulsoft.ap.nb22.domain.usuario

import arrow.core.Either
import org.rulsoft.ap.nb22.core.ApiError
import org.rulsoft.ap.nb22.domain.usuario.model.Usuario

interface UsuarioRemoteRepository {

    suspend fun findUsuarioByEmail(email: String): Either<ApiError, Usuario>

}

package dev.rulsoft.nb22.domain.usuario

import arrow.core.Either
import dev.rulsoft.nb22.common.domain.ApiError
import dev.rulsoft.nb22.domain.usuario.model.Usuario

interface UsuarioRemoteRepository {

    suspend fun findUsuarioByEmail(email: String): Either<ApiError, Usuario>

}

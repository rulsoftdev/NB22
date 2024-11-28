package org.rulsoft.ap.nb22.domain.usuario

import org.rulsoft.ap.nb22.domain.usuario.model.Usuario


interface UsuarioLocalRepository {

    suspend fun findTheOneUser(): Usuario?
    suspend fun insertOrUpdateUser(usuario: Usuario)

}
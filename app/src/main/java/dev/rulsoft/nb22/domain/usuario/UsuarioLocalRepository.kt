package dev.rulsoft.nb22.domain.usuario

import dev.rulsoft.nb22.domain.usuario.model.Usuario


interface UsuarioLocalRepository {

    suspend fun findTheOneUser(): Usuario?
    suspend fun insertOrUpdateUser(usuario: Usuario)

}
package dev.rulsoft.nb22.data.usuario.database

import dev.rulsoft.nb22.data.usuario.mappers.toUsuario
import dev.rulsoft.nb22.data.usuario.mappers.toUsuarioEntity
import dev.rulsoft.nb22.domain.usuario.model.Usuario
import dev.rulsoft.nb22.domain.usuario.UsuarioLocalRepository

class UsuarioLocalDataRepository(private val usuarioDao: UsuarioDao): UsuarioLocalRepository {

    override suspend fun findTheOneUser(): Usuario? {
        val usuario = usuarioDao.findTheOneUser()
        if (usuario == null)
            return null
        return usuario.toUsuario()
    }

    override suspend fun insertOrUpdateUser(usuario: Usuario) {
        usuarioDao.deleteAllUsers()
        usuarioDao.insertOrUpdateUser(
            usuario.toUsuarioEntity()
        )
    }

}
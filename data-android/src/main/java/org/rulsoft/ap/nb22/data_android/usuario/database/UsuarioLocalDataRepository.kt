package org.rulsoft.ap.nb22.data_android.usuario.database

import org.rulsoft.ap.nb22.data_android.usuario.mappers.toUsuario
import org.rulsoft.ap.nb22.data_android.usuario.mappers.toUsuarioEntity
import org.rulsoft.ap.nb22.domain.usuario.model.Usuario
import org.rulsoft.ap.nb22.domain.usuario.UsuarioLocalRepository

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
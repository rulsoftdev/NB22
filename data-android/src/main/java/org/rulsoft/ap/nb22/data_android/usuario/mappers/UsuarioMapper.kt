package org.rulsoft.ap.nb22.data_android.usuario.mappers

import org.rulsoft.ap.nb22.data_android.usuario.database.entity.UsuarioEntity
import org.rulsoft.ap.nb22.domain.usuario.model.Usuario

fun UsuarioEntity.toUsuario(): Usuario {
    return Usuario(
        id = id,
        idTerapeuta = idTerapeuta,
        email = email,
        nombre = nombre,
        recordarEmail = recordarEmail
    )
}

fun Usuario.toUsuarioEntity(): UsuarioEntity {
    return UsuarioEntity(
        id = id,
        idTerapeuta = idTerapeuta,
        email = email,
        nombre = nombre,
        recordarEmail = recordarEmail
    )
}
package dev.rulsoft.nb22.data.usuario.mappers

import dev.rulsoft.nb22.data.usuario.database.entity.UsuarioEntity
import dev.rulsoft.nb22.data.usuario.networking.dto.UsuarioDto
import dev.rulsoft.nb22.domain.usuario.model.Usuario

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

fun UsuarioDto.toUsuario(): Usuario {
    return Usuario(
        id = id,
        idTerapeuta = idTerapeuta,
        email = email,
        nombre = nombre,
        recordarEmail = false
    )
}

package org.rulsoft.ap.nb22.data.usuario.mappers

import org.rulsoft.ap.nb22.data.usuario.networking.dto.UsuarioDto
import org.rulsoft.ap.nb22.domain.usuario.model.Usuario

fun UsuarioDto.toUsuario(): Usuario {
    return Usuario(
        id = id,
        idTerapeuta = idTerapeuta,
        email = email,
        nombre = nombre,
        recordarEmail = false
    )
}

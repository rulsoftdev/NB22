package dev.rulsoft.nb22.data.terapeuta.mappers

import dev.rulsoft.nb22.data.terapeuta.networking.dto.TerapeutaDto
import dev.rulsoft.nb22.domain.terapeuta.model.Terapeuta

fun TerapeutaDto.toTerapeuta(): Terapeuta {
    return Terapeuta(
        id = id,
        nombre = nombre,
        fecha = fecha,
        hora = hora,
        descripcion = descripcion,
        email = email,
        foto = foto,
        terapias = terapias.map { it.toTerapia() }
    )
}
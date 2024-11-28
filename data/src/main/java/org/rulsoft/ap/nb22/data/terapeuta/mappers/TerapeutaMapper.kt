package org.rulsoft.ap.nb22.data.terapeuta.mappers

import org.rulsoft.ap.nb22.data.terapeuta.networking.dto.TerapeutaDto
import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapeuta

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
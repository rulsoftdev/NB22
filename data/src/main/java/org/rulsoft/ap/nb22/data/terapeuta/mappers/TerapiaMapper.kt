package org.rulsoft.ap.nb22.data.terapeuta.mappers

import org.rulsoft.ap.nb22.data.terapeuta.networking.dto.TerapiaDto
import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapia

fun TerapiaDto.toTerapia(): Terapia {
    return Terapia(
        id = id,
        nombre = nombre
    )
}
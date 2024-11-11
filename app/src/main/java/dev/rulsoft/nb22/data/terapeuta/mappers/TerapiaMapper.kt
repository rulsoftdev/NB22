package dev.rulsoft.nb22.data.terapeuta.mappers

import dev.rulsoft.nb22.data.terapeuta.networking.dto.TerapiaDto
import dev.rulsoft.nb22.domain.terapeuta.model.Terapia

fun TerapiaDto.toTerapia(): Terapia {
    return Terapia(
        id = id,
        nombre = nombre
    )
}
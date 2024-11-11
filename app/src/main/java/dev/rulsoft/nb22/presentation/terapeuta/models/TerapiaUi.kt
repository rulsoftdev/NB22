package dev.rulsoft.nb22.presentation.terapeuta.models

import dev.rulsoft.nb22.domain.terapeuta.model.Terapia

data class TerapiaUi(
    val id: Int,
    val nombre: String
)

fun Terapia.toTerapiaUi(): TerapiaUi {
    return TerapiaUi(
        id = id,
        nombre = nombre
    )
}

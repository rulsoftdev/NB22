package org.rulsoft.ap.nb22.presentation.terapeuta.models

import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapia

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

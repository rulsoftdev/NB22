package org.rulsoft.ap.nb22.domain.terapeuta.model

import java.util.Date

data class Terapeuta(
    val id: Int,
    val nombre: String,
    val fecha: Date,
    val hora: Int,
    val descripcion: String,
    val email: String,
    val foto: String,
    val terapias: List<Terapia> = emptyList()
)
package org.rulsoft.ap.nb22.domain.curso.model

import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapeuta

data class Curso(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val descripcion_breve: String,
    val banner: String,
    val pais: String? = null,
    val poblacion: String? = null,
    val fechas: String,
    val horario: String,
    val presencial: Boolean,
    val online: Boolean,
    val habilitado: Boolean,
    val enlace: String? = null,
    val terapeutas: List<Terapeuta> = emptyList()
)
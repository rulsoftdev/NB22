package org.rulsoft.ap.nb22.presentation.curso.models

import org.rulsoft.ap.nb22.domain.curso.model.Curso

data class CursoUi (
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
    val enlace: String? = null
)

fun Curso.toCursoUi(): CursoUi{
    return CursoUi(
        id = id,
        titulo = titulo,
        descripcion = descripcion,
        descripcion_breve = descripcion_breve,
        banner = banner,
        pais = pais,
        poblacion = poblacion,
        fechas = fechas,
        horario = horario,
        presencial = presencial,
        online = online,
        habilitado = habilitado,
        enlace = enlace
    )
}
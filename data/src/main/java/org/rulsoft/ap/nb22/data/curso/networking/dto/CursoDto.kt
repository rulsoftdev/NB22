package org.rulsoft.ap.nb22.data.curso.networking.dto

import org.rulsoft.ap.nb22.data.terapeuta.networking.dto.TerapeutaDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CursoDto(
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
    @SerialName("terapeuta")
    val terapeutas: MutableList<TerapeutaDto> = mutableListOf()
)
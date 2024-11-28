package org.rulsoft.ap.nb22.presentation.terapeuta.models

import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapeuta
import java.util.Date

data class TerapeutaUi (
    val id: Int,
    val nombre: String,
    val fecha: Date,
    val hora: Int,
    val descripcion: String,
    val email: String,
    val foto: String,
    val terapias: List<TerapiaUi> = emptyList()
)

fun Terapeuta.toTerapeutaUi(): TerapeutaUi {
    return TerapeutaUi(
        id = id,
        nombre = nombre,
        fecha = fecha,
        hora = hora,
        descripcion = descripcion,
        email = email,
        foto = foto,
        terapias = terapias.map { it.toTerapiaUi() }
    )
}
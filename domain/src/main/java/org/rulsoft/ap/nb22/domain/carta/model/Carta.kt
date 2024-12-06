package org.rulsoft.ap.nb22.domain.carta.model

import java.util.Date
import kotlin.reflect.full.memberProperties

data class Carta(
    val id: Int,
    val idCliente: Int,
    val fecha: Date,
    val hora: Int,
    val notas: String,
    val fijada: Boolean,
    val alias: String,
    val email: String,
    val idTerapeuta: Int,
    val tipo: TipoCarta,
    val numeroMaxRepeticiones: Int,
    val dia: Int,
    val mes: Int,
    val anyo: Int,
    val pp: Int,
    val pd: Int,
    val ne: Int,
    val ci: Int,
    val ce: Int,
    val cp: Int,
    val cg: Int,
    val ps: Int,
    val pa: Int,
    val be: Int,
    val de: Int,
    val pf: Int,
    val rne: Int,
    val rci: Int,
    val rce: Int,
    val rcp: Int,
    val rup: Int,
    val rua: Int
) {
    companion object {
        private val propiedadesMapa = Carta::class.memberProperties.associateBy { it.name }
    }

    fun obtenerValorPropiedad(nombrePropiedad: String): Any? {
        return propiedadesMapa[nombrePropiedad]?.get(this)
    }
}
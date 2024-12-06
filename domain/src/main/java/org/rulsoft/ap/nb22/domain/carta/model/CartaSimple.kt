package org.rulsoft.ap.nb22.domain.carta.model

import java.util.Date
import kotlin.reflect.full.memberProperties

data class CartaSimple (
    val id: Int,
    val idCliente: Int,
    val alias: String,
    val hora: Int,
    val fecha: Date,
    val fijada: Boolean,
    val esPrincipal: Int,
    val tipo: TipoCarta,
    val pp: Int,
    val pd: Int,
    val ne: Int
){
    fun obtenerValorPropiedad(carta: CartaSimple, nombrePropiedad: String): Any? {
        val propiedades = CartaSimple::class.memberProperties
        val propiedad = propiedades.first { it.name == nombrePropiedad }
        return propiedad.get(carta)
    }
}

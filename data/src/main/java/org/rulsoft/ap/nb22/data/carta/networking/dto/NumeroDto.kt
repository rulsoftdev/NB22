package org.rulsoft.ap.nb22.data.carta.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NumeroDto (
    val id: Int,
    val alias: String,
    val resumen: String,
    @SerialName("resumen_free")
    val resumenFree: String,
    val frase: String,
    val finalidad: String,
    val nombre: String,
    val dia: String,
    @SerialName("mes_equilibrio")
    val mesEquilibrio: String,
    @SerialName("mes_desequilibrio")
    val mesDesequilibrio: String,
    val anyo: String,
    @SerialName("pp_equilibrio")
    val ppEquilibrio: String,
    @SerialName("pp_desequilibrio")
    val ppDesequilibrio: String,
    val pd: String,
    val ne: String,
    val ci: String,
    val ce: String,
    val cp: String,
    val cg: String,
    val ps: String,
    val pa: String,
    val be: String,
    val de: String,
    val pf: String,
    val rne: String,
    val rci: String,
    val rce: String,
    val rcp: String,
    val rup: String,
    val rua: String
)
package dev.rulsoft.nb22.data.carta.networking.dto

import dev.rulsoft.nb22.data.common.networking.serializers.DateSerializer
import dev.rulsoft.nb22.data.types.TipoCarta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
data class CartaDto (
    val id: Int,
    @SerialName("id_cliente")
    val idCliente: Int = -1,  //TODO: corregir los datos, para la carta propia de un terapeuta el idCliente es el idTerapeuta
    @Serializable(with = DateSerializer::class)
    val fecha: Date,
    val hora: Int,
    val notas: String = "",
    val fijada: Boolean,
    val alias: String,
    val email: String,
    @SerialName("id_terapeuta")
    val idTerapeuta: Int,
    val tipo: TipoCarta,
    val numeroMaxRepeticiones: Int = -1,
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
)

data class CartaUpdateDto (
    val id: Int?,
    val fijada: Boolean
)
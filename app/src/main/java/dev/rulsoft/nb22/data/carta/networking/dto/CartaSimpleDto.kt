package dev.rulsoft.nb22.data.carta.networking.dto

import dev.rulsoft.nb22.data.common.networking.serializers.DateSerializer
import dev.rulsoft.nb22.data.types.TipoCarta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class CartaSimpleDto (
    val id: Int,
    @SerialName("id_cliente")
    val idCliente: Int,
    val alias: String,
    val hora: Int,
    @Serializable(with = DateSerializer::class)
    val fecha: Date,
    val fijada: Boolean,
    @SerialName("es_carta_principal")
    val esPrincipal: Int,
    val tipo: TipoCarta,
    val pp: Int,
    val pd: Int,
    val ne: Int
)
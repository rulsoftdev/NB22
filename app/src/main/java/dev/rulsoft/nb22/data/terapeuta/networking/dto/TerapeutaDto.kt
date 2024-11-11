package dev.rulsoft.nb22.data.terapeuta.networking.dto

import dev.rulsoft.nb22.common.data.networking.serializers.DateSerializer
import dev.rulsoft.nb22.data.carta.networking.dto.CartaDto
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class TerapeutaDto(
    val id: Int,
    val nombre: String,
    @Serializable(with = DateSerializer::class)
    val fecha: Date,
    val hora: Int,
    val descripcion: String,
    val email: String = "", //TODO: corregir los datos, ya que no pueden aparecer emails vacios
    val foto: String,
    val carta: CartaDto? = null,
    val terapias: List<TerapiaDto> = emptyList()
)
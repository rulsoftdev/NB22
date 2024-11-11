package dev.rulsoft.nb22.data.usuario.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsuarioDto(
    val id: Int,
    @SerialName("id_terapeuta")
    val idTerapeuta: Int,
    val nombre: String,
    val email: String
)

package dev.rulsoft.nb22.data.usuario.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class FiltroUsuarioDto(
    val email: String
)

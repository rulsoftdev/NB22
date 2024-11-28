package org.rulsoft.ap.nb22.data.terapeuta.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class TerapiaDto(
    val id: Int,
    val nombre: String
)

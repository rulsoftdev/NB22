package org.rulsoft.ap.nb22.data.numero.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NumeroFreeDto(
    @SerialName("resumen_free")
    val resumenFree: String
)

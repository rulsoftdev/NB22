package dev.rulsoft.nb22.domain.usuario.model

data class Usuario (
    val id: Int,
    val idTerapeuta: Int,
    val email: String,
    val nombre: String,
    val recordarEmail: Boolean
)
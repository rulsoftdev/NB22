package org.rulsoft.ap.nb22.data_android.usuario.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity (
    @PrimaryKey()
    val id: Int,
    val idTerapeuta: Int,
    val email: String,
    val nombre: String,
    val recordarEmail: Boolean = false
)
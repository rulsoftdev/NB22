package dev.rulsoft.nb22.data.carta.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.rulsoft.nb22.data.types.TipoCarta
import java.util.Date

@Entity(tableName = "carta")
data class CartaEntity (
    @PrimaryKey()
    val id: Int,
    val idCliente: Int,
    val fecha: Date,
    val hora: Int,
    val notas: String = "",
    val fijada: Boolean,
    val alias: String,
    val email: String,
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
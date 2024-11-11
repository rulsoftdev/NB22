package dev.rulsoft.nb22.common.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.rulsoft.nb22.data.carta.database.CartaDao
import dev.rulsoft.nb22.data.carta.database.entity.CartaEntity
import dev.rulsoft.nb22.data.usuario.database.UsuarioDao
import dev.rulsoft.nb22.data.usuario.database.entity.UsuarioEntity

@Database(
    entities = [CartaEntity::class, UsuarioEntity::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NB22Database : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun cartaDao(): CartaDao
}
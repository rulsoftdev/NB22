package org.rulsoft.ap.nb22.data_android.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.rulsoft.ap.nb22.data_android.carta.database.CartaDao
import org.rulsoft.ap.nb22.data_android.carta.database.entity.CartaEntity
import org.rulsoft.ap.nb22.data_android.usuario.database.UsuarioDao
import org.rulsoft.ap.nb22.data_android.usuario.database.entity.UsuarioEntity

@Database(
    entities = [CartaEntity::class, UsuarioEntity::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NB22Database : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun cartaDao(): CartaDao
}
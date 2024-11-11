package dev.rulsoft.nb22.data.usuario.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.rulsoft.nb22.data.usuario.database.entity.UsuarioEntity


@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuario LIMIT 1")
    suspend fun findTheOneUser(): UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(usuario: UsuarioEntity)

    @Query("DELETE FROM Usuario")
    suspend fun deleteAllUsers()
}
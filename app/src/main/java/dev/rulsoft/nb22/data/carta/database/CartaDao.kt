package dev.rulsoft.nb22.data.carta.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.rulsoft.nb22.data.carta.database.entity.CartaEntity

@Dao
interface CartaDao {
    @Query("SELECT * FROM carta WHERE idCliente = :idUsuario")
    suspend fun fetchCartasFijadas(idUsuario: Int): List<CartaEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCarta(carta: CartaEntity)

    @Query("DELETE FROM Carta")
    suspend fun deleteAllCartas()
}
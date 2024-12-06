package org.rulsoft.ap.nb22.data_android.carta.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.rulsoft.ap.nb22.data_android.carta.database.entity.CartaEntity

@Dao
interface CartaDao {
    @Query("SELECT * FROM carta WHERE idCliente = :idUsuario")
    suspend fun fetchCartasFijadas(idUsuario: Int): List<CartaEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCarta(carta: CartaEntity)

    @Query("DELETE FROM Carta")
    suspend fun deleteAllCartas()
}
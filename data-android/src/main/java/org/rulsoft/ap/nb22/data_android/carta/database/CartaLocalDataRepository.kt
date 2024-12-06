package org.rulsoft.ap.nb22.data_android.carta.database

import org.rulsoft.ap.nb22.data_android.carta.database.entity.CartaEntity
import org.rulsoft.ap.nb22.data_android.carta.mappers.toCarta
import org.rulsoft.ap.nb22.data_android.carta.mappers.toCartaEntity
import org.rulsoft.ap.nb22.domain.carta.model.Carta
import org.rulsoft.ap.nb22.domain.carta.CartaLocalRepository

class CartaLocalDataRepository(private val cartaDao: CartaDao): CartaLocalRepository {

    override suspend fun fetchCartasPropias(idUsuario: Int): List<Carta>? {
        val cartas: List<CartaEntity>? = cartaDao.fetchCartasFijadas(idUsuario)
        if (cartas.isNullOrEmpty())
            return null
        return cartas.map { it.toCarta() }
    }

    override suspend fun insertOrUpdateCarta(carta: Carta) {
        cartaDao.deleteAllCartas()
        cartaDao.insertOrUpdateCarta(carta.toCartaEntity())
    }
}
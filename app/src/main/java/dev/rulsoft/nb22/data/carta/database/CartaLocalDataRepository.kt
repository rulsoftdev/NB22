package dev.rulsoft.nb22.data.carta.database

import dev.rulsoft.nb22.data.carta.database.entity.CartaEntity
import dev.rulsoft.nb22.data.carta.mappers.toCarta
import dev.rulsoft.nb22.data.carta.mappers.toCartaEntity
import dev.rulsoft.nb22.domain.carta.model.Carta
import dev.rulsoft.nb22.domain.carta.CartaLocalRepository

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
package dev.rulsoft.nb22.domain.carta

import dev.rulsoft.nb22.domain.carta.model.Carta

interface CartaLocalRepository {

    suspend fun fetchCartasPropias(idUsuario: Int): List<Carta>?

    suspend fun insertOrUpdateCarta(carta: Carta)
}
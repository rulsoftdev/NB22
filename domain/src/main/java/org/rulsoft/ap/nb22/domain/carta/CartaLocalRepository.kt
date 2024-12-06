package org.rulsoft.ap.nb22.domain.carta

import org.rulsoft.ap.nb22.domain.carta.model.Carta

interface CartaLocalRepository {

    suspend fun fetchCartasPropias(idUsuario: Int): List<Carta>?

    suspend fun insertOrUpdateCarta(carta: Carta)
}
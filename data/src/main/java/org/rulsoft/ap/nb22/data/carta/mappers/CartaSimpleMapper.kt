package org.rulsoft.ap.nb22.data.carta.mappers

import org.rulsoft.ap.nb22.data.carta.networking.dto.CartaSimpleDto
import org.rulsoft.ap.nb22.domain.carta.model.CartaSimple

fun CartaSimpleDto.toCartaSimple(): CartaSimple {
    return CartaSimple(
        id = id,
        idCliente = idCliente,
        alias = alias,
        hora = hora,
        fecha = fecha,
        fijada = fijada,
        esPrincipal = esPrincipal,
        tipo = tipo.toDomainTipoCarta(),
        pp = pp,
        pd = pd,
        ne = ne
    )
}
package dev.rulsoft.nb22.data.carta.mappers

import dev.rulsoft.nb22.data.carta.networking.dto.CartaSimpleDto
import dev.rulsoft.nb22.domain.carta.model.CartaSimple

fun CartaSimpleDto.toCartaSimple(): CartaSimple {
    return CartaSimple(
        id = id,
        idCliente = idCliente,
        alias = alias,
        hora = hora,
        fecha = fecha,
        fijada = fijada,
        esPrincipal = esPrincipal,
        tipo = tipo,
        pp = pp,
        pd = pd,
        ne = ne
    )
}
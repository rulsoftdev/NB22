package dev.rulsoft.nb22.data.carta.mappers

import dev.rulsoft.nb22.data.carta.database.entity.CartaEntity
import dev.rulsoft.nb22.data.carta.networking.dto.CartaDto
import dev.rulsoft.nb22.domain.carta.model.Carta

fun CartaDto.toCarta(): Carta {
    return Carta(
        id = id,
        idCliente = idCliente,
        fecha = fecha,
        hora = hora,
        notas = notas,
        fijada = fijada,
        alias = alias,
        email = email,
        idTerapeuta = idTerapeuta,
        tipo = tipo,
        numeroMaxRepeticiones = numeroMaxRepeticiones,
        dia = dia,
        mes = mes,
        anyo = anyo,
        pp = pp,
        pd = pd,
        ne = ne,
        ci = ci,
        ce = ce,
        cp = cp,
        cg = cg,
        ps = ps,
        pa = pa,
        be = be,
        de = de,
        pf = pf,
        rne = rne,
        rci = rci,
        rce = rce,
        rcp = rcp,
        rup = rup,
        rua = rua
    )
}

fun CartaEntity.toCarta(): Carta {
    return Carta(
        id = id,
        idCliente = idCliente,
        fecha = fecha,
        hora = hora,
        notas = notas,
        fijada = fijada,
        alias = alias,
        email = email,
        idTerapeuta = idTerapeuta,
        tipo = tipo,
        numeroMaxRepeticiones = numeroMaxRepeticiones,
        dia = dia,
        mes = mes,
        anyo = anyo,
        pp = pp,
        pd = pd,
        ne = ne,
        ci = ci,
        ce = ce,
        cp = cp,
        cg = cg,
        ps = ps,
        pa = pa,
        be = be,
        de = de,
        pf = pf,
        rne = rne,
        rci = rci,
        rce = rce,
        rcp = rcp,
        rup = rup,
        rua = rua
    )
}

fun Carta.toCartaEntity(): CartaEntity {
    return CartaEntity(
        id = id,
        idCliente = idCliente,
        fecha = fecha,
        hora = hora,
        notas = notas,
        fijada = fijada,
        alias = alias,
        email = email,
        idTerapeuta = idTerapeuta,
        tipo = tipo,
        numeroMaxRepeticiones = numeroMaxRepeticiones,
        dia = dia,
        mes = mes,
        anyo = anyo,
        pp = pp,
        pd = pd,
        ne = ne,
        ci = ci,
        ce = ce,
        cp = cp,
        cg = cg,
        ps = ps,
        pa = pa,
        be = be,
        de = de,
        pf = pf,
        rne = rne,
        rci = rci,
        rce = rce,
        rcp = rcp,
        rup = rup,
        rua = rua
    )
}
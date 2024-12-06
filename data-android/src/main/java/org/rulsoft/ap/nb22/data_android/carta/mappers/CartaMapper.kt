package org.rulsoft.ap.nb22.data_android.carta.mappers

import org.rulsoft.ap.nb22.data.carta.mappers.toDataTipoCarta
import org.rulsoft.ap.nb22.data.carta.mappers.toDomainTipoCarta
import org.rulsoft.ap.nb22.data_android.carta.database.entity.CartaEntity
import org.rulsoft.ap.nb22.domain.carta.model.Carta

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
        tipo = tipo.toDomainTipoCarta(),
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
        tipo = tipo.toDataTipoCarta(),
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
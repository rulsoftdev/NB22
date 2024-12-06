package org.rulsoft.ap.nb22.data.numero.mappers

import org.rulsoft.ap.nb22.data.numero.networking.dto.NumeroDto
import org.rulsoft.ap.nb22.data.numero.networking.dto.NumeroFreeDto
import org.rulsoft.ap.nb22.domain.numero.model.Numero
import org.rulsoft.ap.nb22.domain.numero.model.NumeroFree

fun NumeroDto.toNumero(): Numero {
    return Numero(
        id = id,
        alias = alias,
        resumen = resumen,
        resumenFree = resumenFree,
        frase = frase,
        finalidad = finalidad,
        nombre = nombre,
        dia = dia,
        mesEquilibrio = mesEquilibrio,
        mesDesequilibrio = mesDesequilibrio,
        anyo = anyo,
        ppEquilibrio = ppEquilibrio,
        ppDesequilibrio = ppDesequilibrio,
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

fun NumeroFreeDto.toNumeroFree(): NumeroFree {
    return NumeroFree(
        resumenFree = resumenFree
    )
}
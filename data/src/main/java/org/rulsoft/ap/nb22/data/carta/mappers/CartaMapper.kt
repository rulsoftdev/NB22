package org.rulsoft.ap.nb22.data.carta.mappers

import org.rulsoft.ap.nb22.data.carta.networking.dto.CartaDto
import org.rulsoft.ap.nb22.domain.carta.model.Carta
import org.rulsoft.ap.nb22.data.types.TipoCarta as DataTipoCarta
import org.rulsoft.ap.nb22.domain.carta.model.TipoCarta as DomainTipoCarta

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

// Extensión para mapear de data a domain
fun DataTipoCarta.toDomainTipoCarta(): DomainTipoCarta {
    return when (this) {
        DataTipoCarta.PRINCIPAL -> DomainTipoCarta.PRINCIPAL
        DataTipoCarta.COMPLEMENTARIA -> DomainTipoCarta.COMPLEMENTARIA
        DataTipoCarta.RELACION -> DomainTipoCarta.RELACION
        DataTipoCarta.MIEMBRO_A -> DomainTipoCarta.MIEMBRO_A
        DataTipoCarta.MIEMBRO_B -> DomainTipoCarta.MIEMBRO_B
    }
}

fun DomainTipoCarta.toDataTipoCarta(): DataTipoCarta {
    return when (this) {
        DomainTipoCarta.PRINCIPAL -> DataTipoCarta.PRINCIPAL
        DomainTipoCarta.COMPLEMENTARIA -> DataTipoCarta.COMPLEMENTARIA
        DomainTipoCarta.RELACION -> DataTipoCarta.RELACION
        DomainTipoCarta.MIEMBRO_A -> DataTipoCarta.MIEMBRO_A
        DomainTipoCarta.MIEMBRO_B -> DataTipoCarta.MIEMBRO_B
    }
}
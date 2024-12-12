package org.rulsoft.ap.nb22.presentation.carta

import org.rulsoft.ap.nb22.domain.carta.model.TipoCarta

sealed class CartaUIEvent {
    data class OnChangedCartaFijada(val id: Int): CartaUIEvent()
    data class OnPaintPilar(val nombre: String, val pilar: Pilar, val tipoCarta: TipoCarta): CartaUIEvent()
    data class OnClickCarta(val punto: Punto, val tipoCarta: TipoCarta): CartaUIEvent()
    data class OnLongClickCarta(val punto: Punto, val tipoCarta: TipoCarta): CartaUIEvent()
    data class InitCarta(val tipoCarta: TipoCarta): CartaUIEvent()
}
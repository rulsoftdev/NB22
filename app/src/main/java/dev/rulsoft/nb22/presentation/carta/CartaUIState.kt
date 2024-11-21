package dev.rulsoft.nb22.presentation.carta

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import dev.rulsoft.nb22.presentation.common.utils.UiText
import dev.rulsoft.nb22.data.carta.networking.dto.CartaDto
import dev.rulsoft.nb22.data.carta.networking.dto.CartaSimpleDto
import dev.rulsoft.nb22.data.types.Figura
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.presentation.carta.composable.getEtiqueta
import java.util.Calendar
import java.util.Date

data class CartaUIState(
    val cartaSelected: CartaDto? = null,
    val cartas: MutableMap<TipoCarta, CartaUI> = hashMapOf(),
    val cartasList: List<CartaSimpleUI> = emptyList(),
    val id: Int? = null,
    val alias: String = "",
    val idCliente: Int? = null,
    val email: String? = "",
    val fecha: Date? = null,
    val fechaSeleccionada: Date? = null,
    val hora: Int? = null,
    val horaSeleccionada: Int? = null,
    val fijada: Boolean = false,
    val processing: Boolean = false,
    val calculated: Boolean = false,
    val error: UiText.StringResource? = null,
    val hayDescripcion: String = "",
    val multipleCartas: Boolean = false,
    val hayCambios: Boolean = true
)
data class CartaSimpleUI(
    val id: Int = -1,
    val alias: String = "",
    val fecha: Date = Calendar.getInstance().time,
    val hora: Int = 0,
    val esPrincipal: Boolean = false,
    val tipoCarta: TipoCarta = TipoCarta.PRINCIPAL,
    val pilares: MutableMap<String, Pilar> = hashMapOf(
        "pp" to Pilar(),
        "ne" to Pilar(),
        "pd" to Pilar()
    ),
    val fijada: Boolean = false
)
fun populateCartaSimpleToUI(cartaSimple: CartaSimpleDto): CartaSimpleUI {
    return CartaSimpleUI(
        id = cartaSimple.id,
        alias = cartaSimple.alias,
        fecha = cartaSimple.fecha,
        hora = cartaSimple.hora,
        fijada = cartaSimple.fijada,
        esPrincipal = cartaSimple.esPrincipal > 0,
        pilares = hashMapOf(
            "pp" to Pilar(etiqueta = getEtiqueta(cartaSimple.pp)),
            "ne" to Pilar(etiqueta = getEtiqueta(cartaSimple.ne)),
            "pd" to Pilar(etiqueta = getEtiqueta(cartaSimple.pd))
        )
    )
}
data class CartaUI(
    val id: Int? = null,
    val alias: String = "",
    val fecha: Date? = null,
    val hora: Int? = null,
    val idCliente: Int? = null,
    val email: String = "",
    val tipoCarta: TipoCarta,
    val notas: String = "", //TODO: las notas o se hacen por cartas o solo se tiene en cuenta las notas en BD de la principal
    val numeroMaxRepeticiones: Int = 0,
    val pilares: MutableMap<String, Pilar> = hashMapOf(
        "dia" to Pilar(),
        "mes" to Pilar(),
        "anyo" to Pilar(),
        "pp" to Pilar(),
        "ne" to Pilar(),
        "pd" to Pilar(tensional = true),
        "ci" to Pilar(),
        "ce" to Pilar(),
        "cp" to Pilar(),
        "cg" to Pilar(),
        "pa" to Pilar(),
        "ps" to Pilar(),
        "de" to Pilar(tensional = true),
        "be" to Pilar(),
        "pf" to Pilar(tensional = true),
        "rne" to Pilar(),
        "rci" to Pilar(),
        "rce" to Pilar(),
        "rcp" to Pilar(),
        "rup" to Pilar(),
        "rua" to Pilar()
    ),
)

data class Pilar (
    val etiqueta: String = "NC",
    val resumen: String = "",
    val valor: Int = -1,
    val figura: Figura? = null,
    val color: Color = Color.Black,
    val stroke: Float = 1f,
    val coordenadas: MutableList<Offset> = mutableListOf(),
    val coordenadasLabel: Punto = Punto(),
    val coordenadasValor: Punto = Punto(),
    val radio: Float? = null,
    val tensional: Boolean = false,
    val numeroTensional: Boolean = false,
    val selected: Boolean = false
)

data class Punto (
    val x: Float = 0f,
    val y: Float = 0f
)
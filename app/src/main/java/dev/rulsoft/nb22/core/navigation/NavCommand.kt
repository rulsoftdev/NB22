package dev.rulsoft.nb22.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BubbleChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import dev.rulsoft.nb22.R
import kotlinx.serialization.Serializable

@Serializable
data object Home
@Serializable
data object PreCarta
@Serializable
data class MiCarta(val email: String)
@Serializable
data class Cartas(val email: String)
@Serializable
data class Carta(val id: Int)
@Serializable
data class CartaFree(val fecha: String)
@Serializable
data object Terapeutas
@Serializable
data class Terapeuta(val id: Int)
@Serializable
data class Curso(val id: Int)

data class NavCommand<T: Any>(
    val route: T,
    @StringRes val title: Int
)

enum class NavItem(
    val navCommand: NavCommand<Any>,
    val icon: ImageVector? = null,
    @StringRes val label: Int? = null
) {
    HOME(NavCommand(Home, R.string.toolbar_title), Icons.Filled.Home, R.string.home),
    CURSO(NavCommand(Curso(-1), R.string.curso)),
    PRECARTA(NavCommand(PreCarta, R.string.toolbar_title), Icons.Filled.BubbleChart, R.string.precarta),
    CARTA(NavCommand(MiCarta(""), R.string.mi_carta)),
    CARTAFREE(NavCommand(CartaFree(""), R.string.carta_free)),
    TERAPEUTAS(NavCommand(Terapeutas, R.string.toolbar_title), Icons.Filled.BubbleChart, R.string.terapeutas),
    TERAPEUTA(NavCommand(Terapeuta(-1), R.string.terapeuta)),
}
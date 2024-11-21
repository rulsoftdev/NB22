package dev.rulsoft.nb22.presentation.navigation

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
data class Cartas(val email: String, val showLista: Boolean)
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
    HOME(NavCommand(Home, R.string.toolbar_title), Icons.Filled.Home, R.string.bottom_nav_home),
    CURSO(NavCommand(Curso(-1), R.string.title_curso)),
    PRECARTA(NavCommand(PreCarta, R.string.toolbar_title), Icons.Filled.BubbleChart, R.string.bottom_nav_precarta),
    CARTAS(NavCommand(Cartas("", false), R.string.title_mis_cartas)),
    CARTA(NavCommand(MiCarta(""), R.string.title_mi_carta)),
    CARTAFREE(NavCommand(CartaFree(""), R.string.title_carta_free)),
    TERAPEUTAS(NavCommand(Terapeutas, R.string.toolbar_title), Icons.Filled.BubbleChart, R.string.bottom_nav_terapeutas),
    TERAPEUTA(NavCommand(Terapeuta(-1), R.string.title_terapeuta)),
}
package dev.rulsoft.nb22.core.navigation.graphs

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.rulsoft.nb22.core.navigation.Carta
import dev.rulsoft.nb22.core.navigation.CartaFree
import dev.rulsoft.nb22.core.navigation.Cartas
import dev.rulsoft.nb22.core.navigation.MiCarta
import dev.rulsoft.nb22.core.navigation.PreCarta
import dev.rulsoft.nb22.core.navigation.Terapeutas
import dev.rulsoft.nb22.presentation.carta.CartaFreeScreen
import dev.rulsoft.nb22.presentation.carta.CartaScreen
import dev.rulsoft.nb22.presentation.carta.PreCartaScreen
import dev.shreyaspatil.capturable.controller.CaptureController
import kotlinx.serialization.Serializable

@Serializable
object CartaGraph

fun NavGraphBuilder.cartaGraph(navController: NavHostController,
                               snackbarHostState: SnackbarHostState) {
    navigation<CartaGraph>(startDestination = PreCarta) {
        composable<PreCarta> {
            PreCartaScreen(
                snackbarHostState = snackbarHostState,
                onAcceder ={
                    navController.navigate(MiCarta(it))
                },
                onCalcular = {
                    navController.navigate(CartaFree(it))
                }
            )
        }
        composable<MiCarta> {
            CartaScreen(
                id = null,
                isMostrarEdadValue = false,
                onShowLista = {}
            )
        }
        composable<Cartas> {
            Text(text = "Cartas")
        }
        composable<CartaFree> {
            CartaFreeScreen(
                onSolicitar = {
                    navController.navigate(Terapeutas)
                }
            )
        }
    }
}
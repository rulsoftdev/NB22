package dev.rulsoft.nb22.presentation.navigation.graphs

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.rulsoft.nb22.presentation.navigation.Carta
import dev.rulsoft.nb22.presentation.navigation.CartaFree
import dev.rulsoft.nb22.presentation.navigation.Cartas
import dev.rulsoft.nb22.presentation.navigation.MiCarta
import dev.rulsoft.nb22.presentation.navigation.PreCarta
import dev.rulsoft.nb22.presentation.navigation.Terapeutas
import dev.rulsoft.nb22.presentation.carta.CartaFreeScreen
import dev.rulsoft.nb22.presentation.carta.CartaListScreen
import dev.rulsoft.nb22.presentation.carta.CartaScreen
import dev.rulsoft.nb22.presentation.carta.PreCartaScreen
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
                isMostrarEdadValue = false,
                onShowLista = { email ->
                    navController.navigate(Cartas(email, true))
                }
            )
        }
        composable<Carta> {
            CartaScreen(
                isMostrarEdadValue = false,
                onShowLista = { email ->
                    navController.navigate(Cartas(email, true))
                }
            )
        }
        composable<Cartas> {
            CartaListScreen(
                onClickCarta = {
                    navController.navigate(Carta(it))
                },
                onTogglePin = { }
            )
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
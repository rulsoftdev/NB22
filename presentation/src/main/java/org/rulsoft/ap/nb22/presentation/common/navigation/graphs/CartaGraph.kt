package org.rulsoft.ap.nb22.presentation.common.navigation.graphs

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.rulsoft.ap.nb22.presentation.common.navigation.Carta
import org.rulsoft.ap.nb22.presentation.common.navigation.CartaFree
import org.rulsoft.ap.nb22.presentation.common.navigation.Cartas
import org.rulsoft.ap.nb22.presentation.common.navigation.MiCarta
import org.rulsoft.ap.nb22.presentation.common.navigation.PreCarta
import org.rulsoft.ap.nb22.presentation.common.navigation.Terapeutas
import org.rulsoft.ap.nb22.presentation.carta.CartaFreeScreen
import org.rulsoft.ap.nb22.presentation.carta.CartaListScreen
import org.rulsoft.ap.nb22.presentation.carta.CartaScreen
import org.rulsoft.ap.nb22.presentation.carta.PreCartaScreen
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
                }
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
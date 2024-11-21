package dev.rulsoft.nb22.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.rulsoft.nb22.presentation.navigation.Terapeuta
import dev.rulsoft.nb22.presentation.navigation.Terapeutas
import dev.rulsoft.nb22.presentation.terapeuta.TerapeutaScreen
import dev.rulsoft.nb22.presentation.terapeuta.TerapeutasScreen
import kotlinx.serialization.Serializable

@Serializable
object TerapeutaGraph

fun NavGraphBuilder.terapeutaGraph(navController: NavHostController) {
    navigation<TerapeutaGraph>(startDestination = Terapeutas) {
        composable<Terapeutas> {
            TerapeutasScreen{
                navController.navigate(Terapeuta(it))
            }
        }
        composable<Terapeuta> {
            TerapeutaScreen()
        }
    }
}
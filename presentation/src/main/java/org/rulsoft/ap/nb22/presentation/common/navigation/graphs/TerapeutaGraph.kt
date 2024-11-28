package org.rulsoft.ap.nb22.presentation.common.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.rulsoft.ap.nb22.presentation.common.navigation.Terapeuta
import org.rulsoft.ap.nb22.presentation.common.navigation.Terapeutas
import org.rulsoft.ap.nb22.presentation.terapeuta.TerapeutaScreen
import org.rulsoft.ap.nb22.presentation.terapeuta.TerapeutasScreen
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
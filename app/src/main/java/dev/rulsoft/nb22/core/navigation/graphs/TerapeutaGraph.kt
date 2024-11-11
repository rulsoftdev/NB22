package dev.rulsoft.nb22.core.navigation.graphs

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.rulsoft.nb22.core.navigation.Curso
import dev.rulsoft.nb22.core.navigation.Home
import dev.rulsoft.nb22.core.navigation.Terapeuta
import dev.rulsoft.nb22.core.navigation.Terapeutas
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
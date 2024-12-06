package org.rulsoft.ap.nb22.presentation.common.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.rulsoft.ap.nb22.presentation.common.navigation.Curso
import org.rulsoft.ap.nb22.presentation.common.navigation.Home
import org.rulsoft.ap.nb22.presentation.common.navigation.Terapeuta
import org.rulsoft.ap.nb22.presentation.curso.CursoScreen
import org.rulsoft.ap.nb22.presentation.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeGraph

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<HomeGraph>(startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onClickCurso = {
                    navController.navigate(Curso(it))
                },
                onClickTerapeuta = {
                    navController.navigate(Terapeuta(it))
                }
            )
        }
        composable<Curso> {
            CursoScreen(
                onClickTerapeuta = {
                    navController.navigate(Terapeuta(it))
                }
            )
        }
    }
}
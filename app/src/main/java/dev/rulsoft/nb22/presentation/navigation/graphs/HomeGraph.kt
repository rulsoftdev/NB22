package dev.rulsoft.nb22.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.rulsoft.nb22.presentation.navigation.Curso
import dev.rulsoft.nb22.presentation.navigation.Home
import dev.rulsoft.nb22.presentation.navigation.Terapeuta
import dev.rulsoft.nb22.presentation.curso.CursoScreen
import dev.rulsoft.nb22.presentation.home.HomeScreen
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
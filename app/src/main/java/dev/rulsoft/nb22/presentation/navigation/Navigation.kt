package dev.rulsoft.nb22.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.rulsoft.nb22.presentation.navigation.graphs.HomeGraph
import dev.rulsoft.nb22.presentation.navigation.graphs.cartaGraph
import dev.rulsoft.nb22.presentation.navigation.graphs.homeGraph
import dev.rulsoft.nb22.presentation.navigation.graphs.terapeutaGraph

private const val TAG = "Navigation"

@Composable
fun Navigation(navController: NavHostController,
               snackbarHostState: SnackbarHostState) {
    NavHost(navController = navController,  startDestination = HomeGraph){
        homeGraph(navController)
        cartaGraph(navController, snackbarHostState)
        terapeutaGraph(navController)
    }
}


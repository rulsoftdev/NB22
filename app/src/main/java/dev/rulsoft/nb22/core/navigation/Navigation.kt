package dev.rulsoft.nb22.core.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.rulsoft.nb22.core.navigation.graphs.HomeGraph
import dev.rulsoft.nb22.core.navigation.graphs.cartaGraph
import dev.rulsoft.nb22.core.navigation.graphs.homeGraph
import dev.rulsoft.nb22.core.navigation.graphs.terapeutaGraph
import dev.shreyaspatil.capturable.controller.CaptureController

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


package org.rulsoft.ap.nb22.presentation.common.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.rulsoft.ap.nb22.presentation.common.navigation.graphs.HomeGraph
import org.rulsoft.ap.nb22.presentation.common.navigation.graphs.cartaGraph
import org.rulsoft.ap.nb22.presentation.common.navigation.graphs.homeGraph
import org.rulsoft.ap.nb22.presentation.common.navigation.graphs.terapeutaGraph

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


package dev.rulsoft.nb22.ui

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.core.navigation.CartaFree
import dev.rulsoft.nb22.core.navigation.Curso
import dev.rulsoft.nb22.core.navigation.Home
import dev.rulsoft.nb22.core.navigation.MiCarta
import dev.rulsoft.nb22.core.navigation.NavItem
import dev.rulsoft.nb22.core.navigation.PreCarta
import dev.rulsoft.nb22.core.navigation.Terapeuta
import dev.rulsoft.nb22.core.navigation.Terapeutas
import dev.rulsoft.nb22.core.navigation.navigatePoppingUpToStartDestination
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG = "NB22AppState"

@Composable
fun rememberNB22AppState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    captureController: CaptureController = rememberCaptureController()
): NB22AppState =  remember(snackbarHostState, navController, coroutineScope, captureController){
    NB22AppState(snackbarHostState, navController, coroutineScope, captureController)
}

class NB22AppState(
    val snackbarHostState: SnackbarHostState,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val captureController: CaptureController
) {
    companion object {
        val BOTTOM_NAV_OPTIONS: List<NavItem> = listOf(NavItem.HOME, NavItem.PRECARTA, NavItem.TERAPEUTAS)
        val BOTTOM_DESTINATIONS = listOf(Home, PreCarta, Terapeutas)
        val UP_DESTINATIONS: List<Any> = listOf(Terapeuta(-1), Curso(-1), MiCarta(""), CartaFree(""))
        val TOP_ACCIONS_DESTINATIONS: List<Any> = emptyList<Any>()//listOf(/*MiCarta(""), CartaFree("")*/)
    }

    val getBottomOptions: List<NavItem>
        @Composable get() = BOTTOM_NAV_OPTIONS

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val showBottomNavigation: Boolean
        @Composable get() {
            return BOTTOM_DESTINATIONS.any { destination ->
                currentDestination?.hierarchy?.any {
                    it.hasRoute(destination::class)
                } == true
            }
        }

    val showUpNavigation: Boolean
        @Composable get() {
            return UP_DESTINATIONS.any { destination ->
                currentDestination?.hierarchy?.any {
                    it.hasRoute(destination::class)
                } == true
            }
        }

    val showUpActions: Boolean
        @Composable get() {
            return TOP_ACCIONS_DESTINATIONS.any { destination ->
                currentDestination?.hierarchy?.any {
                    it.hasRoute(destination::class)
                } == true
            }
        }

    fun onBackClick() {
        navController.popBackStack()
    }

    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand)
    }

    @OptIn(ExperimentalComposeApi::class)
    fun onSendCapture(){
        coroutineScope.launch {
            try {
                val bitmapAsync = captureController.captureAsync(Bitmap.Config.ARGB_8888).await()
                // val bitmap = bitmapAsync
                val context = navController.context
                // This is captured bitmap of a content inside Capturable Composable.
                Log.d("NB22AppState", "bitmap: $bitmapAsync")
                //Bitmap is captured successfully. Do something with it!
                //onResourceReady(context, bitmap.asAndroidBitmap())
            } catch (error: Throwable) {
                Log.e("NB22AppState", "error: $error")
                // Error occurred. Handle it!
            }
        }
    }

    @Composable
    fun getTitleScreen(): Int {
        val navItem = NavItem.entries.find { destination ->
            currentDestination?.hierarchy?.any {
                val route = destination.navCommand.route
                it.hasRoute(route::class)
            } == true
        }
        return navItem?.navCommand?.title ?: R.string.sin_title
    }
}
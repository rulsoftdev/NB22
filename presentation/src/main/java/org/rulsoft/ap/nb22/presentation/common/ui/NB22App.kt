package org.rulsoft.ap.nb22.presentation.common.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import org.rulsoft.ap.nb22.presentation.R
import org.rulsoft.ap.nb22.presentation.common.navigation.Navigation
import org.rulsoft.ap.nb22.presentation.common.navigation.composable.AppBarIcon
import org.rulsoft.ap.nb22.presentation.common.navigation.composable.AppBottomNavigation
import org.rulsoft.ap.nb22.presentation.common.ui.theme.NB22Theme


private const val TAG =  "NB22App"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NB22App(appState: NB22AppState = rememberNB22AppState()){

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    NB22Screen {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = appState.snackbarHostState)
            },
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = appState.getTitleScreen())) },
                    navigationIcon = {
                        if (appState.showUpNavigation) {
                            AppBarIcon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription =  R.string.bottom_back,
                                onClick = {
                                    Log.d(TAG, "Back")
                                    appState.onBackClick()
                                }
                            )
                        }
                    },
                    scrollBehavior = if (appState.showUpNavigation) scrollBehavior else null,
                    actions = {
                        if (appState.showUpActions) {
                        }
                    }
                )
            },
            bottomBar = {
                if (appState.showBottomNavigation) {
                    AppBottomNavigation(
                        bottomNavOptions = appState.getBottomOptions,
                        currentDestination = appState.currentDestination,
                        onNavItemClick = { appState.onNavItemClick(it) })
                }
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ){ padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController, appState.snackbarHostState)
            }
        }
    }

}



@Composable
fun NB22Screen(content: @Composable () -> Unit) {
    NB22Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
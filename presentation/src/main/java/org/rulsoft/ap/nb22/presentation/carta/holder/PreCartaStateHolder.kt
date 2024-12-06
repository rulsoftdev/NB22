package org.rulsoft.ap.nb22.presentation.carta.holder

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

class PreCartaStateHolder(
    val snackbarHostState: SnackbarHostState
) {
    @Composable
    fun showMessageError(message: Int?, onMessageShown: () -> Unit = {}) {
        if (message == null) return
        val error = stringResource(id = message)
        LaunchedEffect(error) {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                error,
                withDismissAction = true,
                duration = SnackbarDuration.Indefinite
            )
        }
    }
}

@Composable
fun rememberPreCartaStateHolder(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): PreCartaStateHolder {
    return remember(snackbarHostState) {
        PreCartaStateHolder(snackbarHostState = snackbarHostState)
    }
}
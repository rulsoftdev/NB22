package org.rulsoft.ap.nb22.presentation.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.rulsoft.ap.nb22.core.ApiError
import org.rulsoft.ap.nb22.core.HttpError
import org.rulsoft.ap.nb22.core.NetworkError
import org.rulsoft.ap.nb22.core.UnknownApiError
import org.rulsoft.ap.nb22.core.logger.CrashlyticsLogger
import org.rulsoft.ap.nb22.presentation.common.snackbar.SnackbarManager
import org.rulsoft.ap.nb22.presentation.common.utils.UiText
import org.rulsoft.ap.nb22.presentation.R
import org.rulsoft.ap.nb22.presentation.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage

open class NB22ViewModel(private val crashlyticsLogger: CrashlyticsLogger) : ViewModel() {

    protected val errorChannel = Channel<UiText>()
    val errors = errorChannel.receiveAsFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        SnackbarManager.showMessage(throwable.toSnackbarMessage())
        crashlyticsLogger.logException(throwable)
    }

    // Simplificada la función launchCatching
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) {
        // Lanzamos la corutina con el exceptionHandler ya global
        CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            block()
        }
    }

    // Simplificada la función parseError
    protected fun parseError(apiError: ApiError) {
        val message = when (apiError) {
            is HttpError -> "HttpError: $apiError"
            is NetworkError -> "Sin conexión a internet"
            is UnknownApiError -> "No se ha procesado correctamente la petición"
        }

        launchCatching {
            val uiMessage = when (apiError) {
                is HttpError -> UiText.DynamicString(message)
                is NetworkError -> UiText.StringResource(resId = R.string.errors_no_hay_conexion, "")
                is UnknownApiError -> UiText.DynamicString(message)
            }
            errorChannel.send(uiMessage)
        }
    }
}

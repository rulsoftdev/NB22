package org.rulsoft.ap.nb22.presentation.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.rulsoft.ap.nb22.presentation.common.snackbar.SnackbarManager
import org.rulsoft.ap.nb22.presentation.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import org.rulsoft.ap.nb22.presentation.common.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.rulsoft.ap.nb22.core.ApiError
import org.rulsoft.ap.nb22.core.HttpError
import org.rulsoft.ap.nb22.core.NetworkError
import org.rulsoft.ap.nb22.core.UnknownApiError
import org.rulsoft.ap.nb22.core.logger.CrashlyticsLogger
import org.rulsoft.ap.nb22.presentation.R


open class NB22ViewModel(private val crashlyticsLogger: CrashlyticsLogger) : ViewModel() {

    protected val errorChannel = Channel<UiText>()
    val errors = errorChannel.receiveAsFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        SnackbarManager.showMessage(throwable.toSnackbarMessage())
        crashlyticsLogger.logException(throwable)
    }

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        launchCatching(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                crashlyticsLogger.logException(throwable)
            },
            block = block
        )

    protected fun parseError(apiError: ApiError) {
        when(apiError){
            is HttpError -> {
                Log.d("CristalpediaViewModel", "HttpError: $apiError")
                launchCatching(exceptionHandler) {
                    errorChannel.send(
                        UiText.DynamicString(
                            "HttpError: $apiError"
                        )
                    )
                }
            }
            is NetworkError -> {
                Log.d("CristalpediaViewModel", "Sin conexión a internet")
                launchCatching(exceptionHandler) {
                    errorChannel.send(
                        UiText.StringResource(
                            resId = R.string.errors_no_hay_conexion,
                            ""
                        )
                    )
                }
            }
            is UnknownApiError -> {
                Log.d("CristalpediaViewModel", "Haremos un crashlitycs")
                launchCatching(exceptionHandler) {
                    errorChannel.send(
                        UiText.DynamicString(
                            "No se ha procesado correctamente la petición"
                        )
                    )
                }
            }
        }
    }

}
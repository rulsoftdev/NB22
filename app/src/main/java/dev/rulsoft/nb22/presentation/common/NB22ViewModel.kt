package dev.rulsoft.nb22.presentation.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.core.ApiError
import dev.rulsoft.nb22.core.HttpError
import dev.rulsoft.nb22.core.NetworkError
import dev.rulsoft.nb22.core.UnknownApiError
import dev.rulsoft.nb22.core.logger.CrashlyticsLogger
import dev.rulsoft.nb22.presentation.common.snackbar.SnackbarManager
import dev.rulsoft.nb22.presentation.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import dev.rulsoft.nb22.presentation.common.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


open class NB22ViewModel(private val crashlyticsLogger: CrashlyticsLogger) : ViewModel() {

    protected val errorChannel = Channel<UiText>()
    val errors = errorChannel.receiveAsFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        SnackbarManager.showMessage(throwable.toSnackbarMessage())
        crashlyticsLogger.logException(throwable)
    }

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
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
                viewModelScope.launch(exceptionHandler) {
                    errorChannel.send(
                        UiText.DynamicString(
                            "HttpError: $apiError"
                        )
                    )
                }
            }
            is NetworkError -> {
                Log.d("CristalpediaViewModel", "Sin conexión a internet")
                viewModelScope.launch(exceptionHandler) {
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
                viewModelScope.launch(exceptionHandler) {
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
package dev.rulsoft.nb22.presentation.carta

import androidx.lifecycle.viewModelScope
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.common.logger.CrashlyticsLogger
import dev.rulsoft.nb22.common.presentation.NB22ViewModel
import dev.rulsoft.nb22.domain.usuario.UsuarioLocalRepository
import dev.rulsoft.nb22.domain.usuario.usecase.UserCheckAndLoadCartasUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PreCartaViewModel (
    logService: CrashlyticsLogger,
    private val userCheckAndLoadCartaUseCase: UserCheckAndLoadCartasUseCase,
    private val localUsuarioLocalRepository: UsuarioLocalRepository
): NB22ViewModel(logService){

    private val _state = MutableStateFlow<PreCartaUIState>(PreCartaUIState.Idle())
    val state: StateFlow<PreCartaUIState> = _state

    // Estado para manejar los valores del email y checkbox
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _recordarEmail = MutableStateFlow(false)
    val recordarEmail: StateFlow<Boolean> = _recordarEmail

    init {
        viewModelScope.launch {
            _state.value = PreCartaUIState.Loading
            val usuario = localUsuarioLocalRepository.findTheOneUser()
            if (usuario != null) {
                _email.value = usuario.email
                _recordarEmail.value = usuario.recordarEmail
            }
            _state.value = PreCartaUIState.Idle()
        }
    }

    fun actualizarCampos(email: String, recordarEmail: Boolean) {
        _email.value = email
        _recordarEmail.value = recordarEmail
    }

    fun saveUsuario(email: String, recordarEmail: Boolean = false){
        viewModelScope.launch {
            _state.value = PreCartaUIState.Loading // Indicamos que la carga está en progreso
            val result = userCheckAndLoadCartaUseCase.invoke(email, recordarEmail)

            when (result) {
                is UserCheckAndLoadCartasUseCase.Result.UserNotFound -> {
                    _state.value = PreCartaUIState.Idle(R.string.usuario_not_found) // Error si el usuario no se encuentra
                }

                is UserCheckAndLoadCartasUseCase.Result.CartaNotFound -> {
                    _state.value = PreCartaUIState.Idle(R.string.carta_not_found) // Error si la carta no se encuentra
                }

                is UserCheckAndLoadCartasUseCase.Result.Success -> {
                    _state.value = PreCartaUIState.Success // Éxito si todo sale bien
                }

                is UserCheckAndLoadCartasUseCase.Result.Error -> {
                    _state.value = PreCartaUIState.Idle(R.string.unknown_error) // Error general
                }
            }
        }
    }

    fun resetState() {
        _state.value = PreCartaUIState.Idle()
    }
}
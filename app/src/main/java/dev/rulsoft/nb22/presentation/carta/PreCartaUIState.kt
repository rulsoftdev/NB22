package dev.rulsoft.nb22.presentation.carta

sealed class PreCartaUIState{
    data class Idle(val errorMessage: Int? = null) : PreCartaUIState()
    object Loading : PreCartaUIState()
    object Success : PreCartaUIState()
}


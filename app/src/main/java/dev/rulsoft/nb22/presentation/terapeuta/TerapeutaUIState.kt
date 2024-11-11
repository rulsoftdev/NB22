package dev.rulsoft.nb22.presentation.terapeuta

import dev.rulsoft.nb22.presentation.terapeuta.models.TerapeutaUi


data class TerapeutaUIState(
    val terapeuta: TerapeutaUi? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
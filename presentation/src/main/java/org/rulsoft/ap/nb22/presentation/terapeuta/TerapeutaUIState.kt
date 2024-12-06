package org.rulsoft.ap.nb22.presentation.terapeuta

import org.rulsoft.ap.nb22.presentation.terapeuta.models.TerapeutaUi


data class TerapeutaUIState(
    val terapeuta: TerapeutaUi? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
package org.rulsoft.ap.nb22.presentation.terapeuta

import org.rulsoft.ap.nb22.presentation.terapeuta.models.TerapeutaUi


data class TerapeutasUIState(
    val terapeutas: List<TerapeutaUi> = emptyList(),
    val isLoading: Boolean = false
)
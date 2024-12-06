package org.rulsoft.ap.nb22.presentation.curso

import org.rulsoft.ap.nb22.presentation.curso.models.CursoUi

data class CursosUIState(
    val cursos: List<CursoUi> = emptyList(),
    val isLoading: Boolean = false
)
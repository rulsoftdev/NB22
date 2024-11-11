package dev.rulsoft.nb22.presentation.curso

import dev.rulsoft.nb22.presentation.curso.models.CursoUi

data class CursosUIState(
    val cursos: List<CursoUi> = emptyList(),
    val isLoading: Boolean = false
)
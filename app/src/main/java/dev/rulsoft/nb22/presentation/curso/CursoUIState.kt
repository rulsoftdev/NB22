package dev.rulsoft.nb22.presentation.curso

import dev.rulsoft.nb22.domain.curso.model.Curso

data class CursoUIState(
    val curso: Curso? = null,
    val isLoading: Boolean = false
)
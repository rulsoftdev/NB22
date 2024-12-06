package org.rulsoft.ap.nb22.presentation.curso

import org.rulsoft.ap.nb22.domain.curso.model.Curso

data class CursoUIState(
    val curso: Curso? = null,
    val isLoading: Boolean = false
)
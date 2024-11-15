package dev.rulsoft.nb22.presentation.curso

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dev.rulsoft.nb22.common.logger.CrashlyticsLogger
import dev.rulsoft.nb22.common.presentation.NB22ViewModel
import dev.rulsoft.nb22.domain.curso.CursoRemoteRepository
import dev.rulsoft.nb22.presentation.curso.models.toCursoUi
import kotlinx.coroutines.launch


class CursosViewModel(
    logService: CrashlyticsLogger,
    private val cursoRemoteRepository: CursoRemoteRepository,
): NB22ViewModel(logService){

    var state by mutableStateOf(CursosUIState())
        private set

    init{
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            cursoRemoteRepository.fetchCursos().fold(
                ifLeft = { apiError ->
                    parseError(apiError)
                },
                ifRight = { cursos ->
                    if (cursos.isNotEmpty()) {
                        state = state.copy(
                            cursos = cursos.map { it.toCursoUi() },
                            isLoading = false
                        )
                    }
                }
            )
        }
    }

}
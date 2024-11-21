package dev.rulsoft.nb22.presentation.curso

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dev.rulsoft.nb22.core.logger.CrashlyticsLogger
import dev.rulsoft.nb22.presentation.common.NB22ViewModel
import dev.rulsoft.nb22.domain.curso.CursoRemoteRepository
import kotlinx.coroutines.launch


class CursoViewModel(
    logService: CrashlyticsLogger,
    private val cursoRemoteRepository: CursoRemoteRepository,
    savedStateHandle: SavedStateHandle
): NB22ViewModel(logService){

    var state by mutableStateOf(CursoUIState())
        private set

    init{
        val id = savedStateHandle.get<Int>("id")
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            if(id != null) {
                cursoRemoteRepository.fetchCursoById(id).fold(
                    ifLeft = { apiError ->
                        parseError(apiError)
                    },
                    ifRight = { curso ->
                        Log.d("CursoViewModel", "curso: $curso")
                        if (curso != null) {
                            state = state.copy(
                                curso = curso,
                                isLoading = false
                            )
                        }
                    }
                )
            }
        }
    }

}
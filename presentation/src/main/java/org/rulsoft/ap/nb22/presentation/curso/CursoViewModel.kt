package org.rulsoft.ap.nb22.presentation.curso

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import org.rulsoft.ap.nb22.core.logger.CrashlyticsLogger
import org.rulsoft.ap.nb22.presentation.common.NB22ViewModel
import org.rulsoft.ap.nb22.domain.curso.CursoRemoteRepository
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.rulsoft.ap.nb22.presentation.common.utils.ApiConstants

@KoinViewModel
class CursoViewModel(
    logService: CrashlyticsLogger,
    private val cursoRemoteRepository: CursoRemoteRepository,
    savedStateHandle: SavedStateHandle
): NB22ViewModel(logService){

    var state by mutableStateOf(CursoUIState())
        private set

    init{
        val id = savedStateHandle.get<Int>("id")
        launchCatching {
            state = state.copy(
                isLoading = true
            )
            if(id != null) {
                cursoRemoteRepository.fetchCursoById(id).fold(
                    ifLeft = { apiError ->
                        parseError(apiError)
                    },
                    ifRight = { curso ->
                        state = state.copy(
                            curso = curso,
                            isLoading = false
                        )
                    }
                )
            }
        }
    }

}
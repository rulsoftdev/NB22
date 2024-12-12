package org.rulsoft.ap.nb22.presentation.terapeuta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import org.rulsoft.ap.nb22.core.logger.CrashlyticsLogger
import org.rulsoft.ap.nb22.presentation.common.NB22ViewModel
import org.rulsoft.ap.nb22.domain.terapeuta.TerapeutaRepository
import org.rulsoft.ap.nb22.presentation.terapeuta.models.toTerapeutaUi
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TerapeutaViewModel (
    logService: CrashlyticsLogger,
    private val terapeutaRepository: TerapeutaRepository,
    savedStateHandle: SavedStateHandle
): NB22ViewModel(logService){

    var state by mutableStateOf(TerapeutaUIState())
        private set

    init{
        val id = savedStateHandle.get<Int>("id")
        launchCatching {
            state = state.copy(
                isLoading = true
            )
            if(id != null) {
                terapeutaRepository.fetchTerapeutaById(id).fold(
                    ifLeft = { apiError ->
                        parseError(apiError)
                    },
                    ifRight = { terapeuta ->
                        state = state.copy(
                            terapeuta = terapeuta.toTerapeutaUi(),
                            isLoading = false
                        )
                    }
                )
            }
        }
    }

}
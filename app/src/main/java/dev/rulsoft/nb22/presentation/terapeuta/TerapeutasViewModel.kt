package dev.rulsoft.nb22.presentation.terapeuta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dev.rulsoft.nb22.core.logger.CrashlyticsLogger
import dev.rulsoft.nb22.presentation.common.NB22ViewModel
import dev.rulsoft.nb22.domain.terapeuta.TerapeutaRepository
import dev.rulsoft.nb22.presentation.terapeuta.models.toTerapeutaUi
import kotlinx.coroutines.launch


class TerapeutasViewModel(
    logService: CrashlyticsLogger,
    private val terapeutaRepository: TerapeutaRepository
): NB22ViewModel(logService){

    var state by mutableStateOf(TerapeutasUIState())
        private set

    init{
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            terapeutaRepository.fetchTerapeutas().fold(
                ifLeft = { apiError ->
                    parseError(apiError)
                },
                ifRight = { terapeutas ->
                    if (terapeutas.isNotEmpty()) {
                        state = state.copy(
                            terapeutas = terapeutas.map { it.toTerapeutaUi() },
                            isLoading = false
                        )
                    }
                }
            )
        }
    }

}
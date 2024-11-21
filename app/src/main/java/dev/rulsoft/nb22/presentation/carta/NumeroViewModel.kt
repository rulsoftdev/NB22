package dev.rulsoft.nb22.presentation.carta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dev.rulsoft.nb22.core.logger.CrashlyticsLogger
import dev.rulsoft.nb22.presentation.common.NB22ViewModel
import dev.rulsoft.nb22.domain.numero.NumeroRemoteRepository
import dev.rulsoft.nb22.presentation.carta.composable.calculaAnyo
import dev.rulsoft.nb22.presentation.carta.composable.reduceValor
import dev.rulsoft.nb22.presentation.carta.composable.sumaPilares
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class NumeroViewModel (
    logService: CrashlyticsLogger,
    private val numeroRemoteRepository: NumeroRemoteRepository,
    savedStateHandle: SavedStateHandle
): NB22ViewModel(logService) {

    var state by mutableStateOf(NumeroUIState())
        private set

    init {
        val fecha: String = savedStateHandle.get<String>("fecha")?: ""
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"));
        val cal: Calendar = Calendar.getInstance()
        cal.time = dateFormat.parse(fecha)!!
        val anyo: Int = cal.get(Calendar.YEAR)
        val mes: Int = cal.get(Calendar.MONTH) + 1
        val dia: Int = cal.get(Calendar.DAY_OF_MONTH)
        val madre: Int = reduceValor(dia)
        val yo: Int = mes
        val padre: Int = calculaAnyo(anyo)
        val pp: Int = sumaPilares(listOf(madre, yo, padre))
        if (fecha.isNotEmpty()) {
            viewModelScope.launch {
                numeroRemoteRepository.fetchResumenFreeById(pp).fold(
                    ifLeft = { apiError ->
                        parseError(apiError)
                    },
                    ifRight = { numero ->
                        state = state.copy(
                            resumenFree = numero.resumenFree,
                            fecha = fecha,
                            madre = madre,
                            yo = yo,
                            padre = padre,
                            pp = pp
                        )
                    }
                )
            }
        }
    }

}
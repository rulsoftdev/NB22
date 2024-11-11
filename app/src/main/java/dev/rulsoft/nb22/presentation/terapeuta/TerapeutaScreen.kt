package dev.rulsoft.nb22.presentation.terapeuta

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import dev.rulsoft.nb22.presentation.terapeuta.composable.TerapeutaDetail
import org.koin.androidx.compose.koinViewModel

@Composable
fun TerapeutaScreen() {
    val vm: TerapeutaViewModel = koinViewModel()
    val state = vm.state

    if (state.isLoading) {
        CircularProgressIndicator()
    }
    if (state.terapeuta != null) {
        TerapeutaDetail(terapeuta = state.terapeuta)
    }

}
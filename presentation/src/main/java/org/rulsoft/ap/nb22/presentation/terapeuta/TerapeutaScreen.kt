package org.rulsoft.ap.nb22.presentation.terapeuta

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.rulsoft.ap.nb22.presentation.terapeuta.composable.TerapeutaDetail

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
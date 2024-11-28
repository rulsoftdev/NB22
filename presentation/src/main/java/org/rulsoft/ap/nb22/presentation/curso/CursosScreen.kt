package org.rulsoft.ap.nb22.presentation.curso

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.rulsoft.ap.nb22.presentation.curso.composable.CursoCard

@Composable
fun CursosScreen(){

    val vm: CursosViewModel = koinViewModel()
    val state = vm.state

    Column(
        modifier = Modifier
            .padding(start = 14.dp, end = 16.dp, top = 64.dp)
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize())
        {
            itemsIndexed(
                items = state.cursos,
                key = { _, item -> item.id }
            ) { index, curso ->
                Row(modifier = Modifier.padding(bottom = 24.dp)) {
                    CursoCard(
                        id = curso.id,
                        banner = curso.banner,
                        titulo = curso.titulo,
                        subtitulo = curso.descripcion,
                        fechas = curso.fechas,
                        isNuevo = index.mod(2) == 0
                    )
                }
            }
        }
    }
}
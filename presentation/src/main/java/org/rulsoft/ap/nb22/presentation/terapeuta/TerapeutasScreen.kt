package org.rulsoft.ap.nb22.presentation.terapeuta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.rulsoft.ap.nb22.presentation.terapeuta.composable.TerapeutaCard

@Composable
fun TerapeutasScreen(
    onClickTerapeuta: (Int) -> Unit
) {
    val vm: TerapeutasViewModel = koinViewModel()
    val state = vm.state

    Column(modifier = Modifier
        .padding(start = 14.dp, end = 16.dp)
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()){
            item{
                Column() {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        text = "Terapeutas",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            itemsIndexed(items = state.terapeutas) {index, item ->
                if(index % 2 == 0){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TerapeutaCard(
                            id = item.id,
                            foto = item.foto,
                            nombre = item.nombre,
                            onClickTerapeuta = onClickTerapeuta
                        )
                        if(index < state.terapeutas.size - 1) {
                            TerapeutaCard(
                                id = state.terapeutas[index + 1].id,
                                foto = state.terapeutas[index + 1].foto,
                                nombre = state.terapeutas[index + 1].nombre,
                                onClickTerapeuta = onClickTerapeuta
                            )
                        }
                    }
                }
            }
        }
    }
}
package org.rulsoft.ap.nb22.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.rulsoft.ap.nb22.presentation.AppVariant
import org.rulsoft.ap.nb22.presentation.R
import org.rulsoft.ap.nb22.presentation.curso.CursosViewModel
import org.rulsoft.ap.nb22.presentation.curso.composable.CursoCard
import org.rulsoft.ap.nb22.presentation.terapeuta.TerapeutasViewModel
import org.rulsoft.ap.nb22.presentation.terapeuta.composable.TerapeutaCard

@Composable
fun HomeScreen(
    onClickCurso: (Int) -> Unit,
    onClickTerapeuta: (Int) -> Unit,
){
    val vm: CursosViewModel = koinViewModel()
    val vmTerapeuta: TerapeutasViewModel = koinViewModel()
    val cursoUIState = vm.state
    val terapuetaUIState = vmTerapeuta.state

    Log.d("HomeScreenFree", "Variant: ${AppVariant.current}")

    Column(
        modifier = Modifier
            .padding(start = 14.dp, end = 16.dp)
    ) {
        val painter: Painter = painterResource(R.drawable.academian22_logo)
        LazyColumn(modifier = Modifier
            .fillMaxSize())
        {
            item{
                Column() {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        text = "Bienvenid@",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Blue
                    )
                }
            }
            itemsIndexed(
                items = cursoUIState.cursos,
                key = { _, item -> item.id }
            ) { index, curso ->
                Row(modifier = Modifier.padding(bottom = 24.dp)) {
                    CursoCard(
                        id = curso.id,
                        banner = curso.banner,
                        titulo = curso.titulo,
                        subtitulo = curso.descripcion_breve,
                        fechas = curso.fechas,
                        isNuevo = index.mod(2) == 0,
                        onClick = {
                            onClickCurso(it)
                        }
                    )
                }
            }
            item{
                Row(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(text = "Terapeutas", style = MaterialTheme.typography.titleLarge)
                }
            }
            item{
                LazyRow(
                    modifier = Modifier.padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    itemsIndexed(
                        items = terapuetaUIState.terapeutas,
                        key = { _, item -> item.id }
                    ) { index, terapeuta ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TerapeutaCard(
                                id = terapeuta.id,
                                foto = terapeuta.foto,
                                nombre = terapeuta.nombre,
                                onClickTerapeuta = onClickTerapeuta)
                        }
                    }
                }
            }
        }
    }
}
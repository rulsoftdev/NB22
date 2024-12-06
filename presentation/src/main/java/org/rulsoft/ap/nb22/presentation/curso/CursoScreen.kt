package org.rulsoft.ap.nb22.presentation.curso

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import org.rulsoft.ap.nb22.domain.terapeuta.model.Terapeuta
import org.rulsoft.ap.nb22.presentation.R
import org.rulsoft.ap.nb22.presentation.common.utils.ApiConstants
import org.rulsoft.ap.nb22.presentation.terapeuta.composable.TerapeutaCard
import java.util.Calendar

@Composable
fun CursoScreen(
    onClickTerapeuta: (Int) -> Unit
){
    val vm: CursoViewModel = koinViewModel()
    val state: CursoUIState = vm.state
    if(state.isLoading){
        CircularProgressIndicator()
    } else {
        if(state.curso != null) {
            CursoDetail(
                banner = state.curso.banner,
                titulo = state.curso.titulo,
                descripcion = state.curso.descripcion,
                pais = state.curso.pais,
                poblacion = state.curso.poblacion,
                enlace = state.curso.enlace,
                fechas = state.curso.fechas,
                horario = state.curso.horario,
                presencial = state.curso.presencial,
                online = state.curso.online,
                terapeutas = state.curso.terapeutas,
                onClickTerapeuta = onClickTerapeuta
            )
        }
    }
}

@Composable
fun CursoDetail(
    banner: String,
    titulo: String,
    descripcion: String,
    pais: String? = null,
    poblacion: String? = null,
    enlace: String? = null,
    fechas: String? = null,
    horario: String? = null,
    presencial: Boolean,
    online: Boolean,
    terapeutas: List<Terapeuta>,
    onClickTerapeuta: (Int) -> Unit
) {
    // ScrollState para mantener el estado de la posición del scroll
    val scrollState = rememberScrollState()
    val handler = LocalUriHandler.current

    val valorStyle = SpanStyle(
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        fontWeight = FontWeight.Normal
    )

    Box(modifier = Modifier
        .fillMaxHeight()
    ){
        Column(modifier = Modifier
            .verticalScroll(scrollState)
        ){
            val painter = // Imagen de reemplazo mientras se carga
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = "${ApiConstants.URL_IMAGE_CURSOS}$banner").apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.academian22_logo) // Imagen de reemplazo mientras se carga
                    }).build()
                )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color(0xFF1E1E1E))
            )
            Column(modifier = Modifier
                .offset(y = (-20).dp)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(MaterialTheme.colorScheme.surface)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 8.dp)
                    ,
                    horizontalArrangement = Arrangement.End
                ){
                    if (presencial) {
                        Badge (
                            modifier = Modifier.padding(end = 8.dp),
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ){
                            Text(text = "Presencial")
                        }
                    }
                    if (online) {
                        Badge(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ) {
                            Text(text = "Online")
                        }
                    }
                }
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                )
                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                )
                Text(
                    text = "Formadores",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                )
                LazyRow(
                    modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    itemsIndexed(
                        items = terapeutas,
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
                Card(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ){
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Fechas: $fechas",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        if(!horario.isNullOrBlank()) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(valorStyle) {
                                        append(
                                            HtmlCompat.fromHtml(
                                                horario,
                                                HtmlCompat.FROM_HTML_MODE_LEGACY
                                            ).toString()
                                        )
                                    }
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        if (presencial) {
                            Text(
                                text = "Ubicación: $poblacion ${pais ?: ""}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = {
                        handler.openUri(enlace?:"https://www.alexpozo.com/")
                    }
                ) {
                    Text(text = "Acceder al curso")
                }
            }
        }
    }
}

@Preview
@Composable
fun CursoScreenPreview(){
    CursoDetail(
        banner = "",
        titulo = "Curso Básico: Autoconocimiento",
        descripcion = "Es el estudio de la información oculta de los números que rigen tu fecha de nacimiento.\n" +
            "En el momento del parto, existe una energía concreta en el planeta tierra, que condiciona nuestra forma de ser, no solo el carácter, si no cómo nos relacionamos con los demás.\n" +
            "Nos dota de habilidades, talentos y dones innatos que tenemos la responsabilidad de desarrollar. También podemos ver qué puntos de tensión tenemos para poder fortalecerlos, y de qué herramientas disponemos para gestionar el estrés.\n" +
            "Es una novedosa herramienta de autoconocimiento tan precisa como apasionante. Con unas sencillas sumas y restas, que cualquiera puede hacer, construimos la carta natal de quien deseemos.\n" +
            "Conocer tu carta natal no te dejará indiferente, marcará un antes y un después en tu vida, ya que una vez sabes cómo eres, cómo funcionas y en qué te puedes realmente convertir, es revelador e inspirador. Una vez tomamos conciencia, podemos tomar decisiones totalmente alineadas con nuestra energía y esencia.\n" +
            "Si eres terapeuta, coach, psicólogo, o tienes una profesión que ayudes a otros en su desarrollo personal y espiritual, con esta herramienta obtendrás una información muy valiosa, que te permitirá acompañar a tus clientes de una forma muy efectiva, directa y sencilla, ahorrando tiempo y ganando más satisfacción de tus clientes.\n" +
            "Sabrás cuales son sus puntos débiles para poder tratarlos directamente, podrás sugerirle herramientas de gestión de estrés totalmente personalizadas para cada cliente, descubrirás sus talentos y dones innatos con los que los podrás inspirar para sacar su mejor versión. Tendrás una guía clara para orientarlos sobre a qué se pueden dedicar profesionalmente para estar alineados con su energía natal.",
        presencial = true,
        online = true,
        terapeutas = listOf(
            Terapeuta(
                id = 1,
                foto = "",
                descripcion = "Numerologo",
                nombre = "Alex Pozo",
                email = "prueba@gmail.com",
                fecha = Calendar.getInstance().time,
                hora = 600
            )
        ),
        poblacion = "Viladecans (Barcelona)",
        pais = "España",
        onClickTerapeuta = {}
    )
}
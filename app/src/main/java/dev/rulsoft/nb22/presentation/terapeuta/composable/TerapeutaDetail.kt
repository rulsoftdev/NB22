package dev.rulsoft.nb22.presentation.terapeuta.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.common.data.util.ApiConstants
import dev.rulsoft.nb22.presentation.terapeuta.models.TerapeutaUi
import java.util.Date

@Composable
fun TerapeutaDetail(
    terapeuta: TerapeutaUi
) {
    // ScrollState para mantener el estado de la posición del scroll
    val scrollState = rememberScrollState()
    //BackPressHandler(onBackPressed = { onBack() })

    /*Scaffold(
        topBar = {
            /*DefaultAppBarCarta(
                title = R.string.empty,
                showSend = false,
                showNotas = false,
                onBack = onBack,
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Botón cerrar"
                        )
                    }
                }
            )*/
        },
        content = { innerPadding ->*/
    Column(
        modifier = Modifier
            //.padding(innerPadding)
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Row (modifier = Modifier
            .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .offset(x = 0.dp, y = 0.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .width(150.dp)
                    .height(200.dp)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .fillMaxWidth(.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val painter = // Imagen de reemplazo mientras se carga
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = "${ApiConstants.URL_IMAGE_TERAPEUTAS}${terapeuta.foto}")
                            .apply(block = fun ImageRequest.Builder.() {
                                placeholder(R.drawable.academian22_logo) // Imagen de reemplazo mientras se carga
                            }).build()
                    )
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .offset(x = 0.dp, y = 120.dp)
            ) {
                Text(
                    text = "Hola,\n" +
                            "Soy ${terapeuta.nombre}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                /*state.terapeuta?.redes.forEach { red ->
                    Column(
                        modifier = Modifier
                            .offset(x = 0.dp, y = 0.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .width(40.dp)
                            .height(40.dp)
                            .fillMaxWidth(.5f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val drawableResId = getDrawableResIdByName("${red}_logo")
                        val painter: Painter = painterResource(id = drawableResId)
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }*/
            }
        }
        Text(
            text = terapeuta.descripcion,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
    }
    /*}
)*/
}

@Composable
fun getDrawableResIdByName(name: String): Int {
    val context = LocalContext.current
    return context.resources.getIdentifier(name, "drawable", context.packageName)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TerapeutaScreenPreview() {
    val terapeuta = TerapeutaUi(
        nombre = "Álex Pozo",
        descripcion = "Te doy la bienvenida a mi web, un espacio de formación con pasión, en el que puedes sentirte como en casa.\n" +
                "En esta web encontrarás información y formaciones de Numerología en Base 22, un regalo para la vida, a través del autoconocimiento.\n" +
                "\n" +
                "Te voy a contar un poco más sobre mí.\n" +
                "Una de mis pasiones es la Formación, poder enseñar y empoderar a otra gente desde la alegría, el amor y el respeto. \n" +
                "\n" +
                "Llevo varios años investigando y desarrollando la Numerología en base 22, que gracias a todas las sesiones individuales y cursos grupales impartidos a distintas partes del mundo, me ha aportado la experiencia necesaria para convertirme en experto en la materia.",
        //redes = listOf("facebook", "instagram", "youtube"),
        foto = "",
        email = "",
        fecha = Date(),
        hora = 0,
        id = 0,
        terapias = emptyList()
    )
    TerapeutaDetail(
        terapeuta = terapeuta
    )
}
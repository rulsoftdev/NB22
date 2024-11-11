package dev.rulsoft.nb22.presentation.curso.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.common.data.util.ApiConstants

@Composable
fun CursoCard(
    isNuevo: Boolean = true,
    id: Int,
    banner: String,
    titulo: String,
    subtitulo: String,
    enlace: String? = null,
    fechas: String? = null,
    onClick: (Int) -> Unit = {}
) {
    // TODO: Etiqueta Nuevo
    // TODO: Imagen con fondo y esquinas redondeadas
    // TODO: Un título y subtítulo
    Column(modifier = Modifier
        .clickable {
            onClick(id)
        }
    ) {
        Box(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .offset(x = 4.dp, y = 0.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color(0xFF1E1E1E)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val painter = // Imagen de reemplazo mientras se carga
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = "${ApiConstants.URL_IMAGE_CURSOS}$banner").apply(block = fun ImageRequest.Builder.() {
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
            if (isNuevo) {
                Text(
                    text = "NUEVO",
                    modifier = Modifier
                        .offset(x = 0.dp, y = 16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 4.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Column(modifier = Modifier.padding(start = 4.dp, top = 8.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = titulo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitulo,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface
            )
            if(!fechas.isNullOrBlank()){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = fechas,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth().padding( top = 8.dp ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CursoCardPreview(){
    Column(modifier = Modifier.padding(16.dp)) {
        CursoCard(
            id = 5,
            banner = "",
            titulo = "Curso básico: Autoconocimiento",
            subtitulo = "Descubre tu verdadero potencial, comprende mejora tu familia, alinéate con tu profesión encuentra tu camino",
            fechas = "1, 2 y 3 de Julio"
        )
    }
}
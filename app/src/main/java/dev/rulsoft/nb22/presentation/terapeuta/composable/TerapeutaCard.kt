package dev.rulsoft.nb22.presentation.terapeuta.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.data.common.util.ApiConstants

@Composable
fun TerapeutaCard (
    id: Int,
    foto: String,
    nombre: String,
    onClickTerapeuta: (Int) -> Unit
){
    Box(
        modifier = Modifier
            .clickable {
                onClickTerapeuta(id)
            }
    ){
        Column(
            modifier = Modifier
                .offset(x = 0.dp, y = 0.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .width(150.dp)
                .height(200.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = // Imagen de reemplazo mientras se carga
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = "${ApiConstants.URL_IMAGE_TERAPEUTAS}$foto").apply(block = fun ImageRequest.Builder.() {
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
                .width(150.dp)
                .height(20.dp)
                .offset(x = 0.dp, y = 180.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = nombre,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TerapuetaCardPreview(){
    Column(modifier = Modifier.padding(16.dp)) {
        TerapeutaCard(
            id = 1,
            foto = "",
            nombre = "Alex Pozo",
            onClickTerapeuta = {}
        )
    }
}
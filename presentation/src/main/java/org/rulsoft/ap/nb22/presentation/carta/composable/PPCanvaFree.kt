package org.rulsoft.ap.nb22.presentation.carta.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import org.rulsoft.ap.nb22.presentation.WindowSize
import org.rulsoft.ap.nb22.presentation.WindowType
import org.rulsoft.ap.nb22.presentation.common.types.TipoPaint

@Composable
fun PPCanvaFree(
    windowSize: WindowSize,
    madre: Int,
    yo: Int,
    padre: Int,
    pp: Int
) {
    val heightCanvas = when (windowSize.height) {
        WindowType.Medium -> 150.dp
        WindowType.Expanded -> 150.dp
        else -> 150.dp
    }
    val colorEtiquetaPilares = MaterialTheme.colorScheme.onSurface
    val colorValoresPilares = MaterialTheme.colorScheme.onTertiary
    val colorValorPP = MaterialTheme.colorScheme.onTertiaryContainer
    val colorPilares = MaterialTheme.colorScheme.tertiary
    val colorPP = MaterialTheme.colorScheme.tertiaryContainer
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightCanvas)
    ) {

        val diametro = size.width * .15f
        val radio = diametro / 2f
        val paddingHorizontal = 16.dp
        val paddingVertical = 28.dp
        val paddingHCanvas = 8.dp
        val signoWith = 24.dp
        val extraRadio = 1.3f
        val coordenadas = listOf<Offset>(
            Offset( x = paddingHorizontal.toPx() + paddingHCanvas.toPx() + radio,
                y = size.height / 2f),
            Offset( x = paddingHorizontal.toPx() + paddingHCanvas.toPx() + diametro
                    + signoWith.toPx() + radio,
                y = size.height / 2f),
            Offset( x = paddingHorizontal.toPx() + paddingHCanvas.toPx() + diametro
                    + signoWith.toPx() + diametro
                    + signoWith.toPx() + radio,
                y = size.height / 2f),
            Offset( x = paddingHorizontal.toPx() + paddingHCanvas.toPx() + diametro
                    + signoWith.toPx() + diametro
                    + signoWith.toPx() + diametro
                    + signoWith.toPx() + (radio * extraRadio),
                y = size.height / 2f)
        )
        drawCircle(
            center = coordenadas[0],
            radius = radio,
            color = colorPilares,
            style = Fill
        )
        drawCircle(
            center = coordenadas[1],
            radius = radio,
            color = colorPilares,
            style = Fill
        )
        drawCircle(
            center = coordenadas[2],
            radius = radio,
            color = colorPilares,
            style = Fill
        )
        drawCircle(
            center = coordenadas[3],
            radius = radio * extraRadio,
            color = colorPP,
            style = Fill
        )
        this.drawIntoCanvas { canvas ->
            val paintPilarEtiqueta = getPaint(TipoPaint.FREE, windowSize, colorEtiquetaPilares)
            val paintPilarValores = getPaint(TipoPaint.FREE, windowSize, colorValoresPilares)
            val paintPilarValorPP = getPaint(TipoPaint.FREE_BOLD, windowSize, colorValorPP)
            canvas.nativeCanvas.drawText(
                "DÍA",
                coordenadas[0].x,
                coordenadas[0].y + (radio * extraRadio) + paddingVertical.toPx(),
                paintPilarEtiqueta
            )
            canvas.nativeCanvas.drawText(
                getEtiqueta(madre),
                coordenadas[0].x,
                coordenadas[0].y + (50f / 2.5f),
                paintPilarValores
            )
            canvas.nativeCanvas.drawText(
                "+",
                coordenadas[0].x + radio + (signoWith.toPx() / 2f),
                coordenadas[0].y + (50f / 2.5f),
                paintPilarEtiqueta
            )
            canvas.nativeCanvas.drawText(
                "MES",
                coordenadas[1].x,
                coordenadas[1].y + (radio * extraRadio) + paddingVertical.toPx(),
                paintPilarEtiqueta
            )
            canvas.nativeCanvas.drawText(
                getEtiqueta(yo),
                coordenadas[1].x,
                coordenadas[1].y + (50f / 2.5f),
                paintPilarValores
            )
            canvas.nativeCanvas.drawText(
                "+",
                coordenadas[1].x + radio + (signoWith.toPx() / 2f),
                coordenadas[1].y + (50f / 2.5f),
                paintPilarEtiqueta
            )
            canvas.nativeCanvas.drawText(
                "AÑO",
                coordenadas[2].x,
                coordenadas[2].y + (radio * extraRadio) + paddingVertical.toPx(),
                paintPilarEtiqueta
            )
            canvas.nativeCanvas.drawText(
                getEtiqueta(padre),
                coordenadas[2].x,
                coordenadas[2].y + (50f / 2.5f),
                paintPilarValores
            )
            canvas.nativeCanvas.drawText(
                "=",
                coordenadas[2].x + radio + (signoWith.toPx() / 2f),
                coordenadas[2].y + (50f / 2.5f),
                paintPilarEtiqueta
            )
            canvas.nativeCanvas.drawText(
                "PP",
                coordenadas[3].x,
                coordenadas[3].y + (radio * extraRadio) + paddingVertical.toPx(),
                paintPilarEtiqueta
            )
            canvas.nativeCanvas.drawText(
                getEtiqueta(pp),
                coordenadas[3].x,
                coordenadas[3].y + (60f / 2.5f),
                paintPilarValorPP
            )
        }
    }
}
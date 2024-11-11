package dev.rulsoft.nb22.presentation.carta.composable

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import dev.rulsoft.nb22.data.types.Figura


fun createPath(figura: Figura, coordenadas: List<Offset>): Path {
    val path = Path()

    when (figura) {
        Figura.TRIANGULO -> {
            if (coordenadas.size == 3) {
                path.moveTo(coordenadas[0].x, coordenadas[0].y)
                path.lineTo(coordenadas[1].x, coordenadas[1].y)
                path.lineTo(coordenadas[2].x, coordenadas[2].y)
                path.close()
            }
        }
        Figura.CIRCULO -> {}
        Figura.ESTRELLA -> {
            if (coordenadas.size == 11) {
                path.moveTo(coordenadas[0].x, coordenadas[0].y)
                for (i in 1 until coordenadas.size) {
                    path.lineTo(coordenadas[i].x, coordenadas[i].y)
                }
                path.close()
            }
        }
    }

    return path
}


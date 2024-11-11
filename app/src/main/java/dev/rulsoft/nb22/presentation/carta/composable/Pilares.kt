package dev.rulsoft.nb22.presentation.carta.composable

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.rulsoft.nb22.data.types.Figura
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.presentation.carta.CartaUI


fun createPilares(cartas: MutableMap<TipoCarta, CartaUI>, ds: DrawScope, tipoCarta: TipoCarta){
    cartas[tipoCarta]!!.pilares.forEach { (_, pilar) ->
        if (pilar.figura == Figura.CIRCULO){
            ds.drawCircle(
                center = pilar.coordenadas[0],
                radius = pilar.radio ?: 100f,
                color = pilar.color,
                style = Stroke(
                    width = pilar.stroke
                )
            )
            if(pilar.selected) {
                ds.drawCircle(
                    center = pilar.coordenadas[0],
                    radius = pilar.radio ?: (100f - pilar.stroke / 2),
                    color = pilar.color.copy(alpha = 0.3f),
                    style = Fill
                )
            }
        } else {
            val path = createPath(pilar.figura ?: Figura.TRIANGULO, pilar.coordenadas)
            ds.drawPath(
                path,
                color = pilar.color,
                style = Stroke(
                    width = pilar.stroke
                )
            )
            if (pilar.selected) {
                path.close()
                ds.drawPath(
                    path, color = pilar.color.copy(alpha = 0.3f),
                    style = Fill
                )
            }
        }
    }
}
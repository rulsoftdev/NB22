package org.rulsoft.ap.nb22.presentation.carta.utils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

class PathDrawer(
    private val sizeTriangle: Float,
    private val yLinea1: Float,
    private val heightTriangle: Float,
    private val xColumna1: Float,
    private val xColumna2: Float,
    private val xColumna3: Float,
    private val xColumna4: Float,
    private val xColumna5: Float,
    private val radio: Float,
    private val paddingH: Float,
    private val strokePathLineas: Float,
    private val separacionMascara: Float,
    private val incPosicionLineasH: Float,
    private val size: Size, // tamaño de la pantalla o del canvas
    private val sizeTriangleAmp: Float, // Si lo usas para el tamaño del triángulo ampliado
    private val incrementoTextPosition: Float, // Si lo usas para ajustar posiciones
    private val textPosition1: Float, // Ajuste de la posición del texto
    private val yLinea4: Float,
    private val yLinea5: Float,
    private val yLinea6: Float,
    private val pathLineas: Path = Path()
) {
    // Método para agregar las líneas horizontales
    private fun agregarLineasHorizontales() {
        pathLineas.moveTo(sizeTriangle, yLinea1 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(xColumna3 - (sizeTriangle / 2f), yLinea1 + heightTriangle + incPosicionLineasH)

        pathLineas.moveTo(xColumna3 + (sizeTriangle / 2f), yLinea1 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(size.width - sizeTriangle, yLinea1 + heightTriangle + incPosicionLineasH)

        pathLineas.moveTo(xColumna2 + (sizeTriangle / 2f), yLinea1 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(xColumna3 - (sizeTriangle / 2f), yLinea1 + heightTriangle + incPosicionLineasH)

        pathLineas.moveTo(xColumna4 - (sizeTriangle / 2f), yLinea1 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(xColumna3 + (sizeTriangle / 2f), yLinea1 + heightTriangle + incPosicionLineasH)
    }

    // Método para agregar las líneas verticales
    private fun agregarLineasVerticales() {
        pathLineas.moveTo(
            xColumna1 + paddingH,
            yLinea1 + (heightTriangle + heightTriangle * .75f) + separacionMascara
        )
        pathLineas.lineTo(
            xColumna1 + paddingH,
            0f + yLinea5 + sizeTriangleAmp / 2 - paddingH
        )

        pathLineas.moveTo(xColumna2, yLinea4 + 40f)
        pathLineas.lineTo(xColumna2, (yLinea5 + sizeTriangle) - sizeTriangle / 2f)

        pathLineas.moveTo(xColumna2, yLinea4 + 40f)
        pathLineas.lineTo(xColumna3, yLinea5 + sizeTriangle - radio)

        pathLineas.moveTo(xColumna4, yLinea4 + 40f)
        pathLineas.lineTo(xColumna4, (yLinea5 + sizeTriangle) - sizeTriangle / 2f)

        pathLineas.moveTo(xColumna4, yLinea4 + 40f)
        pathLineas.lineTo(xColumna3, yLinea5 + sizeTriangle - radio)

        pathLineas.moveTo(
            xColumna5 - paddingH,
            yLinea1 + (heightTriangle + heightTriangle * .75f) + separacionMascara
        )
        pathLineas.lineTo(
            xColumna5 - paddingH,
            0f + yLinea5 + sizeTriangleAmp / 2 - paddingH * 2f
        )
    }

    // Método para agregar las líneas diagonales entre columnas
    private fun agregarLineasDiagonales() {
        pathLineas.moveTo(xColumna3 - (sizeTriangle / 2f), yLinea4 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(xColumna2, (yLinea5 + sizeTriangle) - sizeTriangle / 2f)

        pathLineas.moveTo(xColumna3 + (sizeTriangle / 2f), yLinea4 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(xColumna4, (yLinea5 + sizeTriangle) - sizeTriangle / 2f)

        pathLineas.moveTo(xColumna3 - (sizeTriangle / 2f), yLinea4 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(xColumna2 + 40f, (yLinea6 + sizeTriangle) - sizeTriangle / 2f + 10f)

        pathLineas.moveTo(xColumna3 + (sizeTriangle / 2f), yLinea4 + heightTriangle + incPosicionLineasH)
        pathLineas.lineTo(xColumna4 - 40f, (yLinea6 + sizeTriangle) - sizeTriangle / 2f + 10f)
    }

    // Método para agregar las líneas horizontales de la parte inferior
    private fun agregarLineasInferiores() {
        pathLineas.moveTo(xColumna2 + radio, yLinea6 + sizeTriangle)
        pathLineas.lineTo(xColumna3 - radio, yLinea6 + sizeTriangle)

        pathLineas.moveTo(xColumna3 + radio, yLinea6 + sizeTriangle)
        pathLineas.lineTo(xColumna4 - radio, yLinea6 + sizeTriangle)
    }

    // Método que integra todas las líneas
    fun dibujarLineas() {
        agregarLineasHorizontales()
        agregarLineasVerticales()
        agregarLineasDiagonales()
        agregarLineasInferiores()
    }

}

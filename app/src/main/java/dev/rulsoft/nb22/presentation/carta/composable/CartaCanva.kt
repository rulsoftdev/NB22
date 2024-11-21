package dev.rulsoft.nb22.presentation.carta.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.rulsoft.nb22.presentation.WindowSize
import dev.rulsoft.nb22.presentation.WindowType
import dev.rulsoft.nb22.data.types.Figura
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.presentation.carta.CartaUI
import dev.rulsoft.nb22.presentation.carta.CartaUIEvent
import dev.rulsoft.nb22.presentation.carta.Pilar
import dev.rulsoft.nb22.presentation.carta.Punto
import dev.rulsoft.nb22.presentation.carta.utils.PathDrawer
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun CartaCanva(
    cartas: MutableMap<TipoCarta, CartaUI>,
    onEvent: (CartaUIEvent) -> Unit,
    tipoCarta: TipoCarta,
    windowSize: WindowSize,
    heightCanvas: Dp,  // Pasamos los valores calculados como parámetros
    extrayLinea2: Float,
    extraylinea6: Float,
    incrementoTextPosition: Float
) {
    val tipoCartaPaint = if (tipoCarta == TipoCarta.MIEMBRO_A || tipoCarta == TipoCarta.MIEMBRO_B) {
        TipoCarta.PRINCIPAL
    } else {
        tipoCarta
    }
    val colorPilares = if(tipoCartaPaint == TipoCarta.PRINCIPAL)
                            MaterialTheme.colorScheme.onSurface
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
    val pathLineas = Path()
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightCanvas)
            .padding(top = 20.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { tap ->
                        onEvent(CartaUIEvent.OnClickCarta(Punto(tap.x, tap.y), tipoCartaPaint))
                    },
                    onLongPress = { tap ->
                        onEvent(CartaUIEvent.OnLongClickCarta(Punto(tap.x, tap.y), tipoCartaPaint))
                    }
                )
            }
    ) {

        val strokePath = 1.dp.toPx()
        val strokePathLineas = 1.dp.toPx()
        val strokePathX2 = 2.dp.toPx()
        val separacionMascara = strokePath
        val incPosicionLineasH = 1f
        val sizeTriangle = size.width * .15f
        val heightTriangle = sqrt(
            sizeTriangle.toDouble().pow(2.0)
                    - (sizeTriangle / 2f).toDouble().pow(2.0)
        ).toFloat()
        val sizeTriangleAmp = size.width * .2f
        val heightTriangleAmp = sqrt(
            sizeTriangleAmp.toDouble().pow(2.0)
                    - (sizeTriangleAmp / 2f).toDouble().pow(2.0)
        ).toFloat()
        val paddingH = sizeTriangleAmp / 2f - sizeTriangle / 2f

        val radio = sizeTriangle / 2f

        val xColumna1 = sizeTriangle / 2f
        val xColumna2 = size.width * .28f
        val xColumna3 = size.width * .5f
        val xColumna4 = size.width * .72f
        val xColumna5 = size.width - sizeTriangle / 2f

        val yLinea1 = heightTriangle / 2 - 18f

        val yLinea2 = yLinea1 + heightTriangle + heightTriangle * extrayLinea2
        val yLinea3 = yLinea1 + heightTriangle + heightTriangle + 75f
        val yLinea4 =
            heightTriangle + heightTriangle * .75f + separacionMascara + yLinea3 - 40f
        val yLinea5 =
            heightTriangle + heightTriangle * .75f + separacionMascara + yLinea4

        val yLinea6 = radio * 2f + extraylinea6 + yLinea5

        val textPosition1 = yLinea1 + heightTriangle / 2f
        val textPosition2 = yLinea2 + 100f
        val textPosition3 = yLinea3 + heightTriangle / 2f
        val textPosition4 = yLinea4 + heightTriangle / 2f
        val textPosition5 = yLinea5 + sizeTriangle
        val textPosition6 = yLinea6 + sizeTriangle


        if (cartas[tipoCarta] == null) {
            onEvent(CartaUIEvent.InitCarta(tipoCarta))
        } else {

            if (cartas[tipoCarta]!!.pilares["ps"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "ps",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFF0388fc),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(sizeTriangle / 2f + paddingH, yLinea1),
                                Offset(0f + paddingH, yLinea1 + heightTriangle),
                                Offset(sizeTriangle + paddingH, yLinea1 + heightTriangle)
                            ),
                            coordenadasLabel = Punto(
                                (xColumna1 + paddingH),
                                (textPosition1 - heightTriangle / 2 - (8f + incrementoTextPosition))
                            ),
                            coordenadasValor = Punto(
                                xColumna1 + paddingH,
                                textPosition1 + 40f,//45f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar RUA
            if (cartas[tipoCarta]!!.pilares["rua"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "rua",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    sizeTriangle / 2f + paddingH,
                                    yLinea1 + (heightTriangle + heightTriangle * .75f) + separacionMascara
                                ),
                                Offset(
                                    0f + paddingH,
                                    yLinea1 + heightTriangle + separacionMascara
                                ),
                                Offset(
                                    sizeTriangle + paddingH,
                                    yLinea1 + heightTriangle + separacionMascara
                                )
                            ),
                            coordenadasValor = Punto(
                                xColumna1 + paddingH,
                                textPosition1 + heightTriangle - 8f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar CP
            if (cartas[tipoCarta]!!.pilares["cp"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "cp",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFF6203fc),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    size.width * .5f - (sizeTriangle / 2f),
                                    yLinea1 + heightTriangle
                                ),
                                Offset(
                                    size.width * .5f + (sizeTriangle / 2f),
                                    yLinea1 + heightTriangle
                                ),
                                Offset(
                                    size.width * .5f,
                                    yLinea1
                                )
                            ),
                            coordenadasLabel = Punto(
                                xColumna3,
                                textPosition1 - heightTriangle / 2 - (8f + incrementoTextPosition),
                            ),
                            coordenadasValor = Punto(
                                xColumna3,
                                textPosition1 + 35f + incrementoTextPosition,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar MCP
            if (cartas[tipoCarta]!!.pilares["rcp"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "rcp",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    size.width * .5f - (sizeTriangle / 2f),
                                    yLinea1 + heightTriangle + separacionMascara
                                ),
                                Offset(
                                    size.width * .5f + (sizeTriangle / 2f),
                                    yLinea1 + heightTriangle + separacionMascara
                                ),
                                Offset(
                                    size.width * .5f,
                                    yLinea1 + (heightTriangle + heightTriangle * .75f) + separacionMascara
                                )
                            ),
                            coordenadasValor = Punto(
                                xColumna3,
                                textPosition1 + heightTriangle - 8f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar PA
            if (cartas[tipoCarta]!!.pilares["pa"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "pa",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFF0388fc),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    size.width - sizeTriangle - paddingH,
                                    yLinea1 + heightTriangle
                                ),
                                Offset(size.width - paddingH, yLinea1 + heightTriangle),
                                Offset(
                                    size.width - (sizeTriangle / 2f) - paddingH,
                                    yLinea1
                                )
                            ),
                            coordenadasLabel = Punto(
                                xColumna5 - paddingH,
                                textPosition1 - heightTriangle / 2 - (8f + incrementoTextPosition)
                            ),
                            coordenadasValor = Punto(
                                xColumna5 - paddingH,
                                textPosition1 + 40f,//45f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar RUP
            if (cartas[tipoCarta]!!.pilares["rup"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "rup",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    size.width - sizeTriangle - paddingH,
                                    yLinea1 + heightTriangle + separacionMascara
                                ),
                                Offset(
                                    size.width - paddingH,
                                    yLinea1 + heightTriangle + separacionMascara
                                ),
                                Offset(
                                    size.width - (sizeTriangle / 2f) - paddingH,
                                    yLinea1 + (heightTriangle + heightTriangle * .75f) + separacionMascara
                                )
                            ),
                            coordenadasValor = Punto(
                                xColumna5 - paddingH,
                                textPosition1 + heightTriangle - 8f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }

            val esquinasX = when (windowSize.height) {
                WindowType.Medium -> listOf<Float>(
                    0f, 26f, 85f, 43f, 53f, 0f,
                    -53f, -43f, -85f, -26f, 0f
                )

                WindowType.Expanded -> listOf<Float>(
                    0f, 21f, 70f, 33f, 43f, 0f,
                    -43f, -33f, -70f, -21f, 0f
                )

                else -> listOf<Float>(
                    0f, 26f, 85f, 43f, 53f, 0f,
                    -53f, -43f, -85f, -26f, 0f
                )
            }
            val esquinasY = when (windowSize.height) {
                WindowType.Medium -> listOf<Float>(
                    0f, 54f, 62f, 104f, 164f, 136f,
                    164f, 104f, 62f, 54f, 0f
                )

                WindowType.Expanded -> listOf<Float>(
                    0f, 49f, 57f, 90f, 145f, 117f,
                    145f, 90f, 57f, 49f, 0f
                )

                else -> listOf<Float>(
                    0f, 54f, 62f, 104f, 164f, 136f,
                    164f, 104f, 62f, 54f, 0f
                )
            }
            // Pilar CG
            if (cartas[tipoCarta]!!.pilares["cg"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "cg",
                        Pilar(
                            figura = Figura.ESTRELLA,
                            color = Color(0xFFC5BD2C),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(xColumna3 + esquinasX[0], yLinea2 + esquinasY[0]),
                                Offset(
                                    xColumna3 + esquinasX[1]/* + 26.4f*/,
                                    yLinea2 + esquinasY[1] /*+ 54.4f*/
                                ),
                                Offset(
                                    xColumna3 + esquinasX[2]/* + 85.6f*/,
                                    yLinea2 + esquinasY[2] /*+ 62.4f*/
                                ),
                                Offset(
                                    xColumna3 + esquinasX[3]/* + 43.2f*/,
                                    yLinea2 + esquinasY[3] /*+ 104.8f*/
                                ),
                                Offset(
                                    xColumna3 + esquinasX[4]/* + 53.6f*/,
                                    yLinea2 + esquinasY[4] /*+ 164f*/
                                ),
                                Offset(xColumna3 + esquinasX[5], yLinea2 + esquinasY[5] /*+ 136f*/),
                                Offset(
                                    xColumna3 + esquinasX[6]/* - 53.6f*/,
                                    yLinea2 + esquinasY[6] /*+ 164f*/
                                ),
                                Offset(
                                    xColumna3 + esquinasX[7]/* - 43.2f*/,
                                    yLinea2 + esquinasY[7] /*+ 104.8f*/
                                ),
                                Offset(
                                    xColumna3 + esquinasX[8]/* - 85.6f*/,
                                    yLinea2 + esquinasY[8] /*+ 62.4f*/
                                ),
                                Offset(
                                    xColumna3 + esquinasX[9]/* - 26.4f*/,
                                    yLinea2 + esquinasY[9] /*+ 54.4f*/
                                ),
                                Offset(xColumna3 + esquinasX[10], yLinea2 + esquinasY[10])
                            ),
                            coordenadasLabel = Punto(
                                xColumna3,
                                textPosition2 + 58f + (3 * incrementoTextPosition)
                            ),
                            coordenadasValor = Punto(
                                xColumna3,
                                textPosition2,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar CI
            if (cartas[tipoCarta]!!.pilares["ci"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "ci",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFF0388fc),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    xColumna2 - (sizeTriangle / 2f),
                                    yLinea3 + heightTriangle
                                ),
                                Offset(
                                    xColumna2 + (sizeTriangle / 2f),
                                    yLinea3 + heightTriangle
                                ),
                                Offset(xColumna2, yLinea3)
                            ),
                            coordenadasLabel = Punto(
                                xColumna2,
                                textPosition3 - heightTriangle / 2 - (8f + incrementoTextPosition),
                            ),
                            coordenadasValor = Punto(
                                xColumna2,
                                textPosition3 + 35f + incrementoTextPosition,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar MCI
            if (cartas[tipoCarta]!!.pilares["rci"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "rci",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    xColumna2 - (sizeTriangle / 2f),
                                    (yLinea3 + heightTriangle) + separacionMascara
                                ),
                                Offset(
                                    xColumna2 + (sizeTriangle / 2f),
                                    (yLinea3 + heightTriangle) + separacionMascara
                                ),
                                Offset(
                                    xColumna2,
                                    ((yLinea3 + heightTriangle) + heightTriangle * .75f) + separacionMascara
                                )
                            ),
                            coordenadasValor = Punto(
                                xColumna2,
                                textPosition3 + heightTriangle - 8f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar CE
            if (cartas[tipoCarta]!!.pilares["ce"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "ce",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFF0388fc),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    xColumna4 - (sizeTriangle / 2f),
                                    yLinea3 + heightTriangle
                                ),
                                Offset(
                                    xColumna4 + (sizeTriangle / 2f),
                                    yLinea3 + heightTriangle
                                ),
                                Offset(xColumna4, yLinea3)
                            ),
                            coordenadasLabel = Punto(
                                xColumna4,
                                textPosition3 - heightTriangle / 2 - (8f + incrementoTextPosition),
                            ),
                            coordenadasValor = Punto(
                                xColumna4,
                                textPosition3 + 35f + incrementoTextPosition,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar MCE
            if (cartas[tipoCarta]!!.pilares["rce"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "rce",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    xColumna4 - (sizeTriangle / 2f),
                                    (yLinea3 + heightTriangle) + separacionMascara
                                ),
                                Offset(
                                    xColumna4 + (sizeTriangle / 2f),
                                    (yLinea3 + heightTriangle) + separacionMascara
                                ),
                                Offset(
                                    xColumna4,
                                    ((yLinea3 + heightTriangle) + heightTriangle * .75f) + separacionMascara
                                )
                            ),
                            coordenadasValor = Punto(
                                xColumna4,
                                textPosition3 + heightTriangle - 8f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar NE
            if (cartas[tipoCarta]!!.pilares["ne"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "ne",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFF4F971B),
                            stroke = strokePathX2,
                            coordenadas = mutableListOf(
                                Offset(
                                    size.width * .5f - (sizeTriangle / 2f),
                                    heightTriangle + yLinea4
                                ),
                                Offset(
                                    size.width * .5f + (sizeTriangle / 2f),
                                    heightTriangle + yLinea4
                                ),
                                Offset(size.width * .5f, 0f + yLinea4)
                            ),
                            coordenadasLabel = Punto(
                                xColumna3,
                                textPosition4 - heightTriangle / 2 - (8f + incrementoTextPosition)
                            ),
                            coordenadasValor = Punto(
                                xColumna3,
                                textPosition4 + 35f + incrementoTextPosition,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar MNE
            if (cartas[tipoCarta]!!.pilares["rne"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "rne",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(
                                    size.width * .5f - (sizeTriangle / 2f),
                                    heightTriangle + separacionMascara + yLinea4
                                ),
                                Offset(
                                    size.width * .5f + (sizeTriangle / 2f),
                                    heightTriangle + separacionMascara + yLinea4
                                ),
                                Offset(
                                    size.width * .5f,
                                    (heightTriangle + heightTriangle * .75f) + separacionMascara + yLinea4
                                )
                            ),
                            coordenadasValor = Punto(
                                xColumna3,
                                textPosition4 + heightTriangle - 8f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar PP
            if (cartas[tipoCarta]!!.pilares["pp"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "pp",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFF4F971B),
                            stroke = strokePathX2,
                            coordenadas = mutableListOf(
                                Offset(
                                    size.width - sizeTriangleAmp,
                                    heightTriangleAmp + yLinea5 + sizeTriangleAmp / 2 - paddingH * 2f
                                ),
                                Offset(
                                    size.width,
                                    heightTriangleAmp + yLinea5 + sizeTriangleAmp / 2 - paddingH * 2f
                                ),
                                Offset(
                                    size.width - (sizeTriangleAmp / 2f),
                                    0f + yLinea5 + sizeTriangleAmp / 2 - paddingH * 2f
                                )
                            ),
                            coordenadasLabel = Punto(
                                xColumna5 - paddingH,
                                textPosition5 + radio + 55f + incrementoTextPosition
                            ),
                            coordenadasValor = Punto(
                                xColumna5 - paddingH,
                                textPosition5 + 10f, //20f,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar PD
            if (cartas[tipoCarta]!!.pilares["pd"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "pd",
                        Pilar(
                            figura = Figura.TRIANGULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePathX2,
                            coordenadas = mutableListOf(
                                Offset(
                                    sizeTriangleAmp / 2f,
                                    heightTriangleAmp + yLinea5 + sizeTriangleAmp / 2 - paddingH
                                ),
                                Offset(
                                    sizeTriangleAmp,
                                    0f + yLinea5 + sizeTriangleAmp / 2 - paddingH
                                ),
                                Offset(
                                    0f,
                                    0f + yLinea5 + sizeTriangleAmp / 2 - paddingH
                                )
                            ),
                            coordenadasLabel = Punto(
                                xColumna1 + paddingH,
                                textPosition5 + radio + 55f + incrementoTextPosition,
                            ),
                            coordenadasValor = Punto(
                                xColumna1 + paddingH,
                                textPosition5 + 10f + incrementoTextPosition,
                            )
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar Madre
            if (cartas[tipoCarta]!!.pilares["dia"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "dia",
                        Pilar(
                            figura = Figura.CIRCULO,
                            color = colorPilares,
                            stroke = strokePathX2,
                            coordenadas = mutableListOf(
                                Offset(x = xColumna2, y = yLinea5 + sizeTriangle),
                            ),
                            coordenadasLabel = Punto(
                                xColumna2,
                                textPosition5 + radio + 35f + incrementoTextPosition,
                            ),
                            coordenadasValor = Punto(
                                xColumna2,
                                textPosition5 + 8f + incrementoTextPosition,
                            ),
                            radio = radio
                        ),
                        tipoCarta
                    )
                )
            }
            // YO
            if (cartas[tipoCarta]!!.pilares["mes"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "mes",
                        Pilar(
                            figura = Figura.CIRCULO,
                            color = Color(0xFF4F971B),
                            stroke = strokePathX2,
                            coordenadas = mutableListOf(
                                Offset(x = xColumna3, y = yLinea5 + sizeTriangle),
                            ),
                            coordenadasLabel = Punto(
                                xColumna3,
                                textPosition5 + radio + 35f + incrementoTextPosition,
                            ),
                            coordenadasValor = Punto(
                                xColumna3,
                                textPosition5 + 8f + incrementoTextPosition,
                            ),
                            radio = radio
                        ),
                        tipoCarta
                    )
                )
            }

            // Pilar Padre
            if (cartas[tipoCarta]!!.pilares["anyo"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "anyo",
                        Pilar(
                            figura = Figura.CIRCULO,
                            color = colorPilares,
                            stroke = strokePathX2,
                            coordenadas = mutableListOf(
                                Offset(x = xColumna4, y = yLinea5 + sizeTriangle),
                            ),
                            coordenadasLabel = Punto(
                                xColumna4,
                                textPosition5 + radio + 35f + incrementoTextPosition,
                            ),
                            coordenadasValor = Punto(
                                xColumna4,
                                textPosition5 + 8f + incrementoTextPosition,
                            ),
                            radio = radio
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar PF
            if (cartas[tipoCarta]!!.pilares["pf"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "pf",
                        Pilar(
                            figura = Figura.CIRCULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(x = xColumna2, y = yLinea6 + sizeTriangle),
                            ),
                            coordenadasLabel = Punto(
                                xColumna2,
                                textPosition6 + radio + 35f + incrementoTextPosition,
                            ),
                            coordenadasValor = Punto(
                                xColumna2,
                                textPosition6 + 18f,
                            ),
                            radio = radio
                        ),
                        tipoCarta
                    )
                )
            }
            // Pilar BE
            if (cartas[tipoCarta]!!.pilares["be"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "be",
                        Pilar(
                            figura = Figura.CIRCULO,
                            color = Color(0xFF6203fc),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(x = xColumna3, y = yLinea6 + sizeTriangle)
                            ),
                            coordenadasLabel = Punto(
                                xColumna3,
                                textPosition6 + radio + 35f + incrementoTextPosition,
                            ),
                            coordenadasValor = Punto(
                                xColumna3,
                                textPosition6 + 18f,
                            ),
                            radio = radio
                        ),
                        tipoCarta
                    )
                )
            }

            // Pilar DE
            if (cartas[tipoCarta]!!.pilares["de"]!!.coordenadas.isEmpty()) {
                onEvent(
                    CartaUIEvent.OnPaintPilar(
                        "de",
                        Pilar(
                            figura = Figura.CIRCULO,
                            color = Color(0xFFfc3103),
                            stroke = strokePath,
                            coordenadas = mutableListOf(
                                Offset(x = xColumna4, y = yLinea6 + sizeTriangle)
                            ),
                            coordenadasLabel = Punto(
                                xColumna4,
                                textPosition6 + radio + 35f + incrementoTextPosition,
                            ),
                            coordenadasValor = Punto(
                                xColumna4,
                                textPosition6 + 18f,
                            ),
                            radio = radio
                        ),
                        tipoCarta
                    )
                )
            }

            // Líneas de conexión
            val pathDrawer = PathDrawer(
                sizeTriangle = sizeTriangle,
                yLinea1 = yLinea1,
                heightTriangle = heightTriangle,
                xColumna1 = xColumna1,
                xColumna2 = xColumna2,
                xColumna3 = xColumna3,
                xColumna4 = xColumna4,
                xColumna5 = xColumna5,
                radio = radio,
                paddingH = paddingH,
                strokePathLineas = strokePathLineas,
                separacionMascara = separacionMascara,
                incPosicionLineasH = incPosicionLineasH,
                size = size,
                sizeTriangleAmp = sizeTriangleAmp,
                incrementoTextPosition = incrementoTextPosition,
                textPosition1 = textPosition1,
                yLinea4 = yLinea4,
                yLinea5 = yLinea5,
                yLinea6 = yLinea6,
                pathLineas = pathLineas
            )
            pathDrawer.dibujarLineas()
            drawPath(
                path = pathLineas,
                color = Color(0xFF0388fc),
                style = Stroke(
                    width = strokePathLineas
                )
                //brush = SolidColor(Color.LightGray)
            )

            // Creamos los pilares vacios
            createPilares(cartas, this, tipoCarta)

            // Creamos las etiquetas de los pilares y los valores
            this.drawIntoCanvas { canvas ->
                createLabels(canvas, cartas, windowSize, colorPilares, tipoCarta)
                createValores(canvas, cartas, windowSize, colorPilares, tipoCarta)
            }
        }
    }
}


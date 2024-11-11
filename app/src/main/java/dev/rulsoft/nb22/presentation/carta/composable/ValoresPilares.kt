package dev.rulsoft.nb22.presentation.carta.composable

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import dev.rulsoft.nb22.core.WindowSize
import dev.rulsoft.nb22.core.WindowType
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.data.types.TipoPaint
import dev.rulsoft.nb22.presentation.carta.CartaUI


fun getPaint(type: TipoPaint, windowSize: WindowSize, colorAux: Color): Paint {

    return when(type) {
        TipoPaint.FREE -> Paint().apply {
            color = colorAux.hashCode()
            textAlign = Paint.Align.CENTER
            this.textSize = when (windowSize.height) {
                WindowType.Medium -> 50f
                WindowType.Expanded -> 35f
                else -> 45f
            }
        }
        TipoPaint.FREE_BOLD -> Paint().apply {
            color = colorAux.hashCode()
            textAlign = Paint.Align.CENTER
            this.textSize = when (windowSize.height) {
                WindowType.Medium -> 65f
                WindowType.Expanded -> 40f
                else -> 50f
            }
            this.typeface = Typeface.DEFAULT_BOLD
        }
        TipoPaint.NORMAL -> Paint().apply {
            color = colorAux.hashCode()
            textAlign = Paint.Align.CENTER
            this.textSize = when (windowSize.height) {
                WindowType.Medium -> 45f
                WindowType.Expanded -> 35f
                else -> 45f
            }
        }
        TipoPaint.BOLD -> Paint().apply {
            color = colorAux.hashCode()
            textAlign = Paint.Align.CENTER
            this.textSize = when (windowSize.height) {
                WindowType.Medium -> 45f
                WindowType.Expanded -> 35f
                else -> 45f
            }
            this.typeface = Typeface.DEFAULT_BOLD
        }
        TipoPaint.MAESTRO -> Paint().apply {
            color = colorAux.hashCode()
            textAlign = Paint.Align.CENTER
            this.textSize = when (windowSize.height) {
                WindowType.Medium -> 42f
                WindowType.Expanded -> 32f
                else -> 42f
            }
        }
        TipoPaint.MAESTRO_BOLD -> Paint().apply {
            color = colorAux.hashCode()
            textAlign = Paint.Align.CENTER
            this.textSize = when (windowSize.height) {
                WindowType.Medium -> 41f
                WindowType.Expanded -> 31f
                else -> 41f
            }
            this.typeface = Typeface.DEFAULT_BOLD
        }
        TipoPaint.PILAR -> Paint().apply {
            color = colorAux.hashCode()
            textAlign = Paint.Align.CENTER
            this.textSize = when (windowSize.height) {
                WindowType.Medium -> 40f
                WindowType.Expanded -> 30f
                else -> 40f
            }
        }
    }
}

fun createValores(
    canvas: Canvas,
    cartas: MutableMap<TipoCarta, CartaUI>,
    windowSize: WindowSize,
    colorPilares: Color,
    tipoCarta: TipoCarta
){
    // PD
    val paint = getPaint(TipoPaint.NORMAL, windowSize, colorPilares)
    val paintBold = getPaint(TipoPaint.BOLD, windowSize, colorPilares)
    val paintMaestro = getPaint(TipoPaint.MAESTRO, windowSize, colorPilares)
    val paintMaestroBold = getPaint(TipoPaint.MAESTRO_BOLD, windowSize, colorPilares)

    cartas[tipoCarta]!!.pilares["pd"]?.let { pilar ->
        canvas.nativeCanvas.drawText(
            pintarEtiqueta(pilar.etiqueta),
            pilar.coordenadasValor.x,
            pilar.coordenadasValor.y,
            compruebaRepetido(
                pilar.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint, paintBold
            )
        )
    }

    // Madre
    cartas[tipoCarta]!!.pilares["dia"]?.let { pilar ->
        
            canvas.nativeCanvas.drawText(
                pintarEtiqueta(pilar.etiqueta),
                pilar.coordenadasValor.x,
                pilar.coordenadasValor.y,
                compruebaRepetido(
                    pilar.valor,
                    cartas[tipoCarta]!!.numeroMaxRepeticiones,
                    paint, paintBold
                )
            )
    }

    // YO
    cartas[tipoCarta]!!.pilares["mes"]?.let { pilar ->
            canvas.nativeCanvas.drawText(
                pintarEtiqueta(pilar.etiqueta),
                pilar.coordenadasValor.x,
                pilar.coordenadasValor.y,
                compruebaRepetido(
                    pilar.valor,
                    cartas[tipoCarta]!!.numeroMaxRepeticiones,
                    paint, paintBold
                )
            )
    }

    // Padre
    cartas[tipoCarta]!!.pilares["anyo"]?.let { pilar ->
            canvas.nativeCanvas.drawText(
                pintarEtiqueta(pilar.etiqueta),
                pilar.coordenadasValor.x,
                pilar.coordenadasValor.y,
                compruebaRepetido(
                    pilar.valor,
                    cartas[tipoCarta]!!.numeroMaxRepeticiones,
                    paint, paintBold
                )
            )
    }

    // Resto de pilares (ejemplo con PP)
    cartas[tipoCarta]!!.pilares["pp"]?.let { pilar ->
            canvas.nativeCanvas.drawText(
                pintarEtiqueta(pilar.etiqueta),
                pilar.coordenadasValor.x,
                pilar.coordenadasValor.y,
                if (pilar.etiqueta.contains("/")) {
                    compruebaRepetido(
                        pilar.valor,
                        cartas[tipoCarta]!!.numeroMaxRepeticiones,
                        paintMaestro, paintMaestroBold
                    )
                } else {
                    compruebaRepetido(
                        pilar.valor,
                        cartas[tipoCarta]!!.numeroMaxRepeticiones,
                        paint, paintBold
                    )
                }
            )
    }
    // PF
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["pf"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["pf"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["pf"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["pf"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // BE
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["be"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["be"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["be"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["be"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // DE
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["de"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["de"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["de"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["de"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // NE
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["ne"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["ne"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["ne"]!!.coordenadasValor.y,
        if (cartas[tipoCarta]!!.pilares["ne"]!!.etiqueta.contains("/")) {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ne"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paintMaestro,
                paintMaestroBold
            )
        } else {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ne"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint,
                paintBold
            )
        }
    )
    // MNE
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["rne"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["rne"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["rne"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["rne"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // CI
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["ci"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["ci"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["ci"]!!.coordenadasValor.y,
        if (cartas[tipoCarta]!!.pilares["ci"]!!.etiqueta.contains("/")) {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ci"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paintMaestro,
                paintMaestroBold
            )
        } else {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ci"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint,
                paintBold
            )
        }
    )

    // MCI
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["rci"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["rci"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["rci"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["rci"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // CE
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["ce"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["ce"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["ce"]!!.coordenadasValor.y,
        if (cartas[tipoCarta]!!.pilares["ce"]!!.etiqueta.contains("/")) {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ce"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paintMaestro,
                paintMaestroBold
            )
        } else {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ce"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint,
                paintBold
            )
        }
    )

    // MCE
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["rce"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["rce"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["rce"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["rce"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // CG
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["cg"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["cg"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["cg"]!!.coordenadasValor.y,
        if (cartas[tipoCarta]!!.pilares["cg"]!!.etiqueta.contains("/")) {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["cg"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paintMaestro,
                paintMaestroBold
            )
        } else {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["cg"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint,
                paintBold
            )
        }
    )

    // PS
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["ps"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["ps"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["ps"]!!.coordenadasValor.y,
        if (cartas[tipoCarta]!!.pilares["ps"]!!.etiqueta.contains("/")) {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ps"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paintMaestro,
                paintMaestroBold
            )
        } else {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["ps"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint,
                paintBold
            )
        }
    )

    // RUA
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["rua"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["rua"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["rua"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["rua"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // CP
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["cp"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["cp"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["cp"]!!.coordenadasValor.y,
        if (cartas[tipoCarta]!!.pilares["cp"]!!.etiqueta.contains("/")) {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["cp"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paintMaestro,
                paintMaestroBold
            )
        } else {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["cp"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint,
                paintBold
            )
        }
    )
    // MCP
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["rcp"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["rcp"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["rcp"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["rcp"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )

    // PA
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["pa"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["pa"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["pa"]!!.coordenadasValor.y,
        if (cartas[tipoCarta]!!.pilares["pa"]!!.etiqueta.contains("/")) {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["pa"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paintMaestro,
                paintMaestroBold
            )
        } else {
            compruebaRepetido(
                cartas[tipoCarta]!!.pilares["pa"]!!.valor,
                cartas[tipoCarta]!!.numeroMaxRepeticiones,
                paint,
                paintBold
            )
        }
    )

    // RUP
    canvas.nativeCanvas.drawText(
        pintarEtiqueta(cartas[tipoCarta]!!.pilares["rup"]!!.etiqueta),
        cartas[tipoCarta]!!.pilares["rup"]!!.coordenadasValor.x,
        cartas[tipoCarta]!!.pilares["rup"]!!.coordenadasValor.y,
        compruebaRepetido(
            cartas[tipoCarta]!!.pilares["rup"]!!.valor,
            cartas[tipoCarta]!!.numeroMaxRepeticiones,
            paint,
            paintBold
        )
    )
}

fun compruebaRepetido(valor: Int, numeroMaxRepeticiones: Int, paint: Paint, paintBold: Paint): Paint {
    return  if(valor > 0 && sumaDigitos(valor) == numeroMaxRepeticiones)
        paintBold
    else
        paint
}

fun pintarEtiqueta(etiqueta: String): String {
    return if (etiqueta == "NC") "" else etiqueta
}

/*fun compruebaRepetidoStyle(valor: Int, numeroMaxRepeticiones: Int, paint: Paint, paintBold: Paint): TextStyle {
    return  if(sumaDigitos(valor) == numeroMaxRepeticiones)
        TextStyle(
            fontWeight = FontWeight.Bold
        )
    else
        TextStyle(
            fontWeight = FontWeight.Normal
        )
}*/
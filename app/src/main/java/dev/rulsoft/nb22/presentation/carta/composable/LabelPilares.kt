package dev.rulsoft.nb22.presentation.carta.composable

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import dev.rulsoft.nb22.core.WindowSize
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.data.types.TipoPaint
import dev.rulsoft.nb22.presentation.carta.CartaUI


fun createLabels(canvas: Canvas, cartas: MutableMap<TipoCarta, CartaUI>, windowSize: WindowSize,
                 colorPilares: Color, tipoCarta: TipoCarta){
    val paintPilar = getPaint(TipoPaint.NORMAL, windowSize, colorPilares)
    canvas.nativeCanvas.drawText(
        "PD",
        cartas[tipoCarta]!!.pilares["pd"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["pd"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "DÍA",
        cartas[tipoCarta]!!.pilares["dia"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["dia"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "MES",
        cartas[tipoCarta]!!.pilares["mes"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["mes"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "AÑO",
        cartas[tipoCarta]!!.pilares["anyo"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["anyo"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "PP",
        cartas[tipoCarta]!!.pilares["pp"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["pp"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "PF",
        cartas[tipoCarta]!!.pilares["pf"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["pf"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "BE",
        cartas[tipoCarta]!!.pilares["be"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["be"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "DE",
        cartas[tipoCarta]!!.pilares["de"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["de"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "NE",
        cartas[tipoCarta]!!.pilares["ne"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["ne"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "CI",
        cartas[tipoCarta]!!.pilares["ci"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["ci"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "CE",
        cartas[tipoCarta]!!.pilares["ce"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["ce"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "CG",
        cartas[tipoCarta]!!.pilares["cg"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["cg"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "PS",
        cartas[tipoCarta]!!.pilares["ps"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["ps"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "CP",
        cartas[tipoCarta]!!.pilares["cp"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["cp"]!!.coordenadasLabel.y,
        paintPilar
    )
    canvas.nativeCanvas.drawText(
        "PA",
        cartas[tipoCarta]!!.pilares["pa"]!!.coordenadasLabel.x,
        cartas[tipoCarta]!!.pilares["pa"]!!.coordenadasLabel.y,
        paintPilar
    )
}
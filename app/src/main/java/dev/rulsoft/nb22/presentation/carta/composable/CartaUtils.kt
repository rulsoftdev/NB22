package dev.rulsoft.nb22.presentation.carta.composable

import android.content.Context
import android.util.Log

fun getEtiquetaSinBase (etiqueta: String): Int{
    val sEtiqueta = etiqueta.split("/")
    return  sEtiqueta[0].toInt()
}

fun getEtiqueta (valor: Int): String{
    return  if (valor <= 22)
        "$valor"
    else if (valor == 26 || valor == 30 || valor == 33 || valor == 40)
        "$valor/${calculaBase(valor)}"
    else
        "${calculaBase(valor)}"
}

fun calculaBase (valor: Int): Int{
    return  if(valor > 22)
        sumaDigitos(valor)
    else
        valor
}

fun sumaDigitos (valor: Int): Int{
    var resultado = 0
    val digitos = valor.toString()
    digitos.toCharArray().forEach {
        resultado += it.digitToInt()
    }
    return resultado
}

fun restaPilares(pilares: List<Int>) : Int{
    val sortPilares = pilares.sortedByDescending { calculaBase(it) }
    var resultado: Int = restaDosValores(sortPilares[0], sortPilares[1])
    if(sortPilares.size > 2) {
        for (i in 2 until sortPilares.size) {
            resultado =
                if (resultado > calculaBase(sortPilares[i]))
                    restaDosValores(resultado, sortPilares[i])
                else
                    restaDosValores(sortPilares[i], resultado)
        }
    }
    return resultado
}

fun sumaPilares (pilares: List<Int>) : Int{
    var resultado = 0
    pilares.forEach { pilar ->
        resultado += calculaBase(pilar)
    }
    return reduceValor(resultado)
}

fun restaDosValores(valorA: Int, valorB: Int): Int{
    val resultado: Int = calculaBase(valorA) - calculaBase(valorB)
    return if(resultado == 0) 22 else resultado
}

fun calculaAnyo (anyo: Int): Int{
    var valorAnyo = sumaDigitos(anyo)
    return  reduceValor(valorAnyo)
}

fun reduceValor (valor: Int): Int {
    return if (valor > 22
        && valor != 26
        && valor != 30
        && valor != 33
        && valor != 40
    ) {
        var resultado = 0
        val sValor = valor.toString()
        sValor.toCharArray().forEach {
            resultado += it.digitToInt()
        }
        resultado
    } else
        valor
}

fun getImageNumero(tag: String, context: Context): Int {
    var drawableImage = tag
        .replace(" ", "_")
        .replace("-", "_")
    Log.d("CartaUtils", "drawableImage: $drawableImage")
    val intImage = context.resources.getIdentifier(
        drawableImage,
        "drawable",
        context.packageName
    )
    /*if (intImage == null){
        intImage == context.resources.getIdentifier(
            "calculando",
            "drawable",
            context.packageName
        )
    }*/
    return intImage
}
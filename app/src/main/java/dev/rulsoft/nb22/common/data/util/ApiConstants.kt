package dev.rulsoft.nb22.common.data.util

import dev.rulsoft.nb22.BuildConfig
import dev.rulsoft.nb22.R

object ApiConstants {
    const val BASE_URL = BuildConfig.BASE_URL
    val API_KEY = R.string.api_key
    const val URL_IMAGE_CURSOS = "${BASE_URL}imagenes/cursos/"
    const val URL_IMAGE_TERAPEUTAS = "${BASE_URL}imagenes/terapeutas/"
    const val END_POINTS_GET_CARTAS = "cartas"
    const val END_POINTS_GET_CARTAS_BY_CODIGO = "cartas/"
    const val END_POINTS_GET_CARTAS_BY_ID = "cartas/cliente"
    const val END_POINTS_POST_CARTAS_BY_EMAIL = "cartas/cliente"
    const val END_POINTS_POST_CARTAS_SIMPLES_BY_EMAIL = "cartas/cliente/simple"
    const val END_POINTS_GET_CARTAS_RELACION = "cartas/relacion"
    const val END_POINTS_POST_CARTA = "cartas"
    const val END_POINTS_PUT_CARTA = "cartas"
    const val END_POINTS_GET_NUMEROS = "numeros"
    const val END_POINTS_GET_NUMERO = "numeros"
    const val END_POINTS_GET_NUMERO_PP_FREE = "numeros/free"
    const val END_POINTS_GET_CURSOS = "cursos"
    const val END_POINTS_GET_CURSO = "cursos"
    const val END_POINTS_GET_TERAPEUTAS = "terapeutas"
    const val END_POINTS_GET_TERAPEUTA = "terapeutas"
    // const val END_POINTS_GET_USUARIO = "clientes/{id}"
    const val END_POINTS_GET_USUARIO_BY_EMAIL = "clientes/check-info"


}
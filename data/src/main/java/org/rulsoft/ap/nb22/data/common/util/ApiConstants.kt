package org.rulsoft.ap.nb22.data.common.util

import org.rulsoft.ap.nb22.data.common.Config

object ApiConstants {
    val BASE_URL = Config.BASE_URL
    val API_KEY = Config.API_KEY
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
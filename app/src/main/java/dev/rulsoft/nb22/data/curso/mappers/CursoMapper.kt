package dev.rulsoft.nb22.data.curso.mappers

import dev.rulsoft.nb22.data.curso.networking.dto.CursoDto
import dev.rulsoft.nb22.data.terapeuta.mappers.toTerapeuta
import dev.rulsoft.nb22.domain.curso.model.Curso

fun CursoDto.toCurso(): Curso {
    return Curso(
        id = id,
        titulo = titulo,
        descripcion = descripcion,
        descripcion_breve = descripcion_breve,
        banner = banner,
        pais = pais,
        poblacion = poblacion,
        fechas = fechas,
        horario = horario,
        presencial = presencial,
        online = online,
        habilitado = habilitado,
        enlace = enlace,
        terapeutas = terapeutas.map { it.toTerapeuta() }
    )
}
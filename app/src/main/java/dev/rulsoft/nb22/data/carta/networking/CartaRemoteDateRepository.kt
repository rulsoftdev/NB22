package dev.rulsoft.nb22.data.carta.networking

import arrow.core.Either
import dev.rulsoft.nb22.common.data.networking.BaseRemoteDataRepository
import dev.rulsoft.nb22.common.domain.ApiError
import dev.rulsoft.nb22.data.carta.mappers.toCarta
import dev.rulsoft.nb22.data.carta.mappers.toCartaSimple
import dev.rulsoft.nb22.data.carta.networking.dto.CartaUpdateDto
import dev.rulsoft.nb22.data.carta.networking.dto.FiltrosCartasDto
import dev.rulsoft.nb22.domain.carta.model.Carta
import dev.rulsoft.nb22.domain.carta.CartaRemoteRepository
import dev.rulsoft.nb22.domain.carta.model.CartaSimple


class CartaRemoteDataRemoteRepository(private val cartaService: CartaServiceKtor):
    BaseRemoteDataRepository(), CartaRemoteRepository {

    override suspend fun fetchCartas(): Either<ApiError, List<Carta>> {
        val response = cartaService.fetchCartas()
        return handleResponse(response) { cartaDtoList ->
            cartaDtoList.map { it.toCarta() }
        }
    }

    override suspend fun fetchCartaById(id: Int): Either<ApiError, List<Carta>> {
        val response = cartaService.fetchCartasById(id)
        return handleResponse(response) { cartaDtoList ->
            cartaDtoList.map { it.toCarta() }
        }
    }

    override suspend fun findCartasByCodigo(codigo: String): Either<ApiError, List<Carta>> {
        val response = cartaService.findCartasByCodigo(codigo)
        return handleResponse(response) { cartaDtoList ->
            cartaDtoList.map { it.toCarta() }
        }
    }

    override suspend fun findCartasByEmail(email: String): Either<ApiError, List<Carta>> {
        val response = cartaService.findCartasByEmail(FiltrosCartasDto(email))
        return handleResponse(response) { cartaDtoList ->
            cartaDtoList.map { it.toCarta() }
        }
    }

    override suspend fun findCartasSimplesByEmail(email: String): Either<ApiError, List<CartaSimple>> {
        val response = cartaService.findCartasSimplesByEmail(FiltrosCartasDto(email))
        return handleResponse(response) { cartaSimpleDtoList ->
            cartaSimpleDtoList.map { it.toCartaSimple() }
        }
    }

    override suspend fun findCartaDtosRelacion(ids: String): Either<ApiError, List<Carta>> {
        val response = cartaService.findCartaDtosRelacion(ids)
        return handleResponse(response) { cartaDtoList ->
            cartaDtoList.map { it.toCarta() }
        }
    }

    override suspend fun updateCarta(id: Int, fijada: Boolean): Either<ApiError, Carta> {
        val response = cartaService.updateCartaFijada(CartaUpdateDto(id, fijada))
        return handleResponse(response) { cartaDto ->
            cartaDto.toCarta()
        }
    }

}

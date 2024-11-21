package dev.rulsoft.nb22.presentation.carta

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dev.rulsoft.nb22.core.logger.CrashlyticsLogger
import dev.rulsoft.nb22.presentation.common.NB22ViewModel
import dev.rulsoft.nb22.presentation.WindowSize
import dev.rulsoft.nb22.presentation.WindowType
import dev.rulsoft.nb22.data.types.Figura
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.domain.carta.model.Carta
import dev.rulsoft.nb22.domain.carta.CartaRemoteRepository
import dev.rulsoft.nb22.domain.carta.model.CartaSimple
import dev.rulsoft.nb22.presentation.carta.composable.calculaBase
import dev.rulsoft.nb22.presentation.carta.composable.getEtiqueta
import dev.rulsoft.nb22.presentation.carta.composable.sumaDigitos
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class CartaViewModel (
    logService: CrashlyticsLogger,
    private val cartaRemoteRepository: CartaRemoteRepository,
    savedStateHandle: SavedStateHandle
): NB22ViewModel(logService) {

    var state by mutableStateOf(CartaUIState())
        private set

    // val cartaCalculosState: State<CartaCalculosState> = _cartaCalculosState
    private var _repeticionesNumeros: HashMap<Int, Int> = hashMapOf()

    init {
        val id: Int = savedStateHandle.get<Int>("id")?: 0
        if (id > 0) {
            getCartaById(id)
        } else {
            val showLista: Boolean = savedStateHandle.get<Boolean>("showLista") ?: false
            // Log.d("CartaViewModel", "showLista: $showLista")
            var email: String = savedStateHandle.get<String>("email") ?: ""
            if (email.isNotBlank()) {
                viewModelScope.launch {
                    if (showLista) {
                        getCartasList(email)
                    } else {
                        getCartaFijada(email)
                    }
                }
            }
        }
    }

    fun onEvent(event: CartaUIEvent) {
        when (event) {
            // is CartaUIEvent.OnSelectedCarta -> onSelectedCarta(event.id)
            is CartaUIEvent.OnChangedCartaFijada -> onChangedCartaFijada(event.id)
            is CartaUIEvent.OnPaintPilar -> onPaintPilar(event.nombre, event.pilar, event.tipoCarta)
            is CartaUIEvent.OnClickCarta -> onClickCarta(event.punto, event.tipoCarta)
            is CartaUIEvent.OnLongClickCarta -> onLongClickCarta(event.punto, event.tipoCarta)
            is CartaUIEvent.InitCarta -> {
                state = state.copy(
                    cartas = state.cartas.toMutableMap().apply {
                        put(event.tipoCarta, CartaUI(tipoCarta = event.tipoCarta))
                    }
                )
            }
        }
    }

    private fun getCartaFijada(email: String){
        if(email != ""){
            state = state.copy(
                processing = true,
                error = null,
            )
            viewModelScope.launch {
                cartaRemoteRepository.findCartasByEmail(email).fold(
                    ifLeft = { apiError ->
                        parseError(apiError)
                    },
                    ifRight = {cartas ->
                        if(cartas.isNotEmpty()){
                            cartas.forEach { cartaRecuperada ->
                                val tipoCarta = cartaRecuperada.tipo
                                var cartaExistente = state.cartas[tipoCarta]
                                if (cartaExistente == null) {
                                    cartaExistente = CartaUI(
                                        id = cartaRecuperada.id,
                                        fecha = cartaRecuperada.fecha,
                                        alias = cartaRecuperada.alias,
                                        hora = cartaRecuperada.hora,
                                        email = cartaRecuperada.email,
                                        tipoCarta = tipoCarta
                                    )
                                } else {
                                    cartaExistente = cartaExistente.copy(
                                        id = cartaRecuperada.id,
                                        fecha = cartaRecuperada.fecha,
                                        alias = cartaRecuperada.alias,
                                        hora = cartaRecuperada.hora,
                                        email = cartaRecuperada.email,
                                        // Copiar los demás campos de la carta existente que no necesitan actualización
                                    )
                                }
                                val pilaresActualizados = actualizarPilares(cartaExistente.pilares, cartaRecuperada)
                                cartaExistente.pilares.putAll(pilaresActualizados)
                                state = state.copy(
                                    cartas = state.cartas.toMutableMap().apply {
                                        put(tipoCarta, cartaExistente)
                                    }
                                )
                            }
                            state = state.copy(
                                id = cartas[0].id,
                                fecha = cartas[0].fecha,
                                hora = cartas[0].hora,
                                alias = cartas[0].alias,
                                email = email,
                                multipleCartas = cartas.size > 1
                            )
                        } else {
                            //TODO: devolver un error para que no avance
                        }
                    }
                )
            }
        }
    }

    private fun getCartaById(id: Int){
        state = state.copy(
            processing = true,
            error = null,
        )
        viewModelScope.launch {
            cartaRemoteRepository.fetchCartaById(id).fold(
                ifLeft = { apiError ->
                    parseError(apiError)
                },
                ifRight = {cartas ->
                    if(cartas.isNotEmpty()){
                        //TODO: hay que cargar todas las cartas FALLA las multiples
                        cartas.forEach { cartaRecuperada ->
                            val tipoCarta = cartaRecuperada.tipo
                            var cartaExistente = state.cartas[tipoCarta]
                            if (cartaExistente == null) {
                                cartaExistente = CartaUI(
                                    id = cartaRecuperada.id,
                                    fecha = cartaRecuperada.fecha,
                                    alias = cartaRecuperada.alias,
                                    hora = cartaRecuperada.hora,
                                    email = cartaRecuperada.email,
                                    tipoCarta = tipoCarta
                                )
                            } else {
                                cartaExistente = cartaExistente.copy(
                                    id = cartaRecuperada.id,
                                    fecha = cartaRecuperada.fecha,
                                    alias = cartaRecuperada.alias,
                                    hora = cartaRecuperada.hora,
                                    email = cartaRecuperada.email,
                                    // Copiar los demás campos de la carta existente que no necesitan actualización
                                )
                            }
                            val pilaresActualizados = actualizarPilares(cartaExistente.pilares, cartaRecuperada)
                            cartaExistente.pilares.putAll(pilaresActualizados)
                            state = state.copy(
                                cartas = state.cartas.toMutableMap().apply {
                                    put(tipoCarta, cartaExistente)
                                }
                            )
                        }
                        state = state.copy(
                            id = cartas[0].id,
                            fecha = cartas[0].fecha,
                            hora = cartas[0].hora,
                            alias = cartas[0].alias,
                            email = cartas[0].email,
                            multipleCartas = cartas.size > 1
                        )
                    }
                }
            )
        }
    }


    private fun getCartasList(email: String){
        Log.d("CartaViewModel", "Cargamos las cartas del email: $email")
        if(email != null){
            state = state.copy(
                processing = true,
                error = null,
            )
            viewModelScope.launch {
                cartaRemoteRepository.findCartasSimplesByEmail(email).fold(
                    ifLeft = { apiError ->
                        parseError(apiError)
                    },
                    ifRight = {cartas ->
                        // Log.d("CartaViewModel", "Cartas: $cartas")
                        if(cartas.isNotEmpty()) {
                            val cartasActualizadas = cartas.map { carta ->
                                val cartaNueva = CartaSimpleUI(
                                    id = carta.id,
                                    fecha = carta.fecha,
                                    hora = carta.hora,
                                    alias = carta.alias,
                                    esPrincipal = carta.esPrincipal > 0,
                                    tipoCarta = carta.tipo
                                )
                                val pilaresActualizados = cargarPilaresList(cartaNueva.pilares, carta)
                                cartaNueva.pilares.putAll(pilaresActualizados)
                                cartaNueva
                            }
                            state = state.copy(
                                cartasList = cartasActualizadas,
                                email = email
                            )
                        }
                    }
                )
            }
        }
    }


    private fun onLongClickCarta(punto: Punto, tipoCarta: TipoCarta) {
        var longClick = false
        state.cartas[tipoCarta]!!.pilares.mapValues { (etiqueta, pilar) ->
            val teHanLongClicado: Boolean = when (pilar.figura) {
                Figura.TRIANGULO -> {
                    puntoDentroTriangulo(
                        pilar.coordenadas[0],
                        pilar.coordenadas[1],
                        pilar.coordenadas[2],
                        punto
                    )
                }
                Figura.CIRCULO -> {
                    puntoDentroCirculo(
                        pilar.coordenadas[0],
                        pilar.radio ?: 100f,
                        punto
                    )
                }
                Figura.ESTRELLA -> {
                    puntoDentroEstrella(
                        pilar.coordenadas,
                        punto
                    )
                }
                else -> false
            }
            if (!longClick && teHanLongClicado) {
                longClick = true
                state = state.copy(
                    hayDescripcion = "Descripcion de $etiqueta"
                )
            }
        }
    }

    private fun onClickCarta(punto: Punto, tipoCarta: TipoCarta) {
        val pilaresActualizados = state.cartas.getValue(tipoCarta).pilares.mapValues { (etiqueta, pilar) ->
            val teHanClicado: Boolean = when (pilar.figura) {
                Figura.TRIANGULO -> {
                    puntoDentroTriangulo(
                        pilar.coordenadas[0],
                        pilar.coordenadas[1],
                        pilar.coordenadas[2],
                        punto
                    )
                }
                Figura.CIRCULO -> {
                    puntoDentroCirculo(
                        pilar.coordenadas[0],
                        pilar.radio ?: 100f,
                        punto
                    )
                }
                Figura.ESTRELLA -> {
                    puntoDentroEstrella(
                        pilar.coordenadas,
                        punto
                    )
                }
                else -> false
            }
            if (teHanClicado) {
                pilar.copy(selected = !pilar.selected)
            } else {
                pilar
            }
        }

        val nuevasCartas = state.cartas.toMutableMap()
        val cartaActualizada = state.cartas.getValue(tipoCarta).copy(pilares = pilaresActualizados.toMutableMap())
        nuevasCartas[tipoCarta] = cartaActualizada

        state = state.copy(cartas = nuevasCartas)
    }

    private fun onPaintPilar(nombre: String, pilar: Pilar, tipoCarta: TipoCarta) {
        val cartasActualizadas = state.cartas.toMutableMap().apply {
            val carta = this[tipoCarta]
            carta?.let {
                val pilarExistente = it.pilares[nombre]
                if (pilarExistente != null) {
                    // Actualización del pilar con los nuevos valores
                    val pilarNuevo = pilarExistente.copy(
                        coordenadas = pilar.coordenadas,
                        coordenadasLabel = pilar.coordenadasLabel,
                        coordenadasValor = pilar.coordenadasValor,
                        radio = pilar.radio,
                        color = pilar.color,
                        stroke = pilar.stroke,
                        tensional = pilar.tensional,
                        figura = pilar.figura,
                        selected = pilar.selected
                    )
                    // Actualiza el pilar dentro de la carta
                    this[tipoCarta] = it.copy(
                        pilares = it.pilares.toMutableMap().apply {
                            put(nombre, pilarNuevo)
                        }
                    )
                }
            }
        }
        // Actualiza el estado con las cartas actualizadas
        state = state.copy(cartas = cartasActualizadas)
    }

    //TODO: descomentar cuando se añada el listado de cartas, si se requiere
    private fun onChangedCartaFijada(id: Int){
        viewModelScope.launch {
            cartaRemoteRepository.updateCarta(id, true)
        }
    }

    private fun puntoDentroTriangulo(A: Offset, B: Offset, C: Offset, P: Punto): Boolean {
        val areaTriangulo = 0.5 * Math.abs(A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y))
        val areaSubtrianguloPBC = 0.5 * Math.abs(P.x * (B.y - C.y) + B.x * (C.y - P.y) + C.x * (P.y - B.y))
        val areaSubtrianguloPAC = 0.5 * Math.abs(A.x * (P.y - C.y) + P.x * (C.y - A.y) + C.x * (A.y - P.y))
        val areaSubtrianguloPAB = 0.5 * Math.abs(A.x * (B.y - P.y) + B.x * (P.y - A.y) + P.x * (A.y - B.y))
        return (areaSubtrianguloPBC + areaSubtrianguloPAC + areaSubtrianguloPAB).roundToInt() == areaTriangulo.roundToInt()
    }

    private fun puntoDentroCirculo(centro: Offset, radio: Float, P: Punto): Boolean {
        val distanciaSquare = (P.x - centro.x) * (P.x - centro.x) + (P.y - centro.y) * (P.y - centro.y)
        return distanciaSquare <= radio * radio
    }

    private fun puntoDentroEstrella(coordenadas: List<Offset>, P: Punto): Boolean {
        var dentro = false
        var i = 0
        var j = coordenadas.size - 1

        while (i < coordenadas.size) {
            val puntoI = coordenadas[i]
            val puntoJ = coordenadas[j]
            if ((puntoI.y > P.y) != (puntoJ.y > P.y) &&
                P.x < (puntoJ.x - puntoI.x) * (P.y - puntoI.y) / (puntoJ.y - puntoI.y) + puntoI.x
            ) {
                dentro = !dentro
            }
            j = i
            i++
        }
        return dentro
    }

    /*private fun onSelectedCarta(id: Int){
        viewModelScope.launch {
            /*cartaRepository.getCarta(id)
                .catch { ex ->
                    state = CartaState(error = ex.message)
                }
                .collect { carta ->
                    state = CartaState(
                        cartaSelected = carta,
                        id = carta.id,
                        fecha = carta.fecha,
                        hora = carta.hora,
                        alias = carta.alias,
                        notas = carta.notas,
                        urlImage = carta.urlImage
                    )
                }*/
        }
    }*/


    private fun getNumeroMaxRepeticiones(): Int {
        val result = _repeticionesNumeros.toList().sortedBy { (_, value) -> value}.toMap()
        val lastKey = result.keys.last()
        val keys = result.keys.toList()
        val prevLastKey = keys[keys.size-2]
        return  if(result[lastKey]!! > result[prevLastKey]!!)
            result.keys.last()
        else
            0
    }

    private fun actualizaRepeticiones(valor: Int) {
        var hValor = _repeticionesNumeros[sumaDigitos(valor)]
        if (hValor == null)
            hValor = 1
        else
            hValor += 1
        _repeticionesNumeros[sumaDigitos(valor)] = hValor
    }

    private fun actualizarPilares(pilaresExistente: MutableMap<String, Pilar>,
                                  carta: Carta
    ): Map<String, Pilar> {
        val pilaresActualizados = pilaresExistente.toMutableMap()

        pilaresExistente.forEach { key, pilar ->
            //Log.d("CartaViewModel", "Key: $key -> Pilar: $pilar")
            val valor = carta.obtenerValorPropiedad(carta, key) as Int
            //Log.d("CartaViewModel", "(Key:$key) => $valor")
            val pilarActualizado = pilar.copy(
                etiqueta = getEtiqueta(valor),
                valor = calculaBase(valor)
                // Actualiza los demás campos del pilar según sea necesario
            )
            actualizaRepeticiones(valor)
            pilaresActualizados[key] = pilarActualizado
        }
        return pilaresActualizados
    }

    private fun cargarPilaresList(pilaresExistente: MutableMap<String, Pilar>,
                                  carta: CartaSimple
    ): Map<String, Pilar> {
        val pilaresActualizados = pilaresExistente.toMutableMap()

        pilaresExistente.forEach { key, pilar ->
            //Log.d("CartaViewModel", "Key: $key -> Pilar: $pilar")
            val valor = carta.obtenerValorPropiedad(carta, key) as Int
            //Log.d("CartaViewModel", "(Key:$key) => $valor")
            val pilarActualizado = pilar.copy(
                etiqueta = getEtiqueta(valor),
                valor = calculaBase(valor)
                // Actualiza los demás campos del pilar según sea necesario
            )
            pilaresActualizados[key] = pilarActualizado
        }
        return pilaresActualizados
    }

    fun calcularValores(windowSize: WindowSize): Pair<Dp, Triple<Float, Float, Float>> {
        val heightCanvas = when (windowSize.height) {
            WindowType.Medium -> 500.dp
            WindowType.Expanded -> 650.dp
            else -> 465.dp
        }

        val extraYLinea2 = when (windowSize.height) {
            WindowType.Medium -> 1f
            WindowType.Expanded -> .85f
            else -> 1f
        }

        val extraYLinea6 = when (windowSize.height) {
            WindowType.Medium -> 150f
            WindowType.Expanded -> 100f
            else -> 150f
        }

        val incrementoTextPosition = when (windowSize.height) {
            WindowType.Medium -> 10f
            WindowType.Expanded -> 0f
            else -> 10f
        }

        return heightCanvas to Triple(extraYLinea2, extraYLinea6, incrementoTextPosition)
    }



}
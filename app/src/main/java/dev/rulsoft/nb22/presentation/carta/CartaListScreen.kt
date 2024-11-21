package dev.rulsoft.nb22.presentation.carta

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.presentation.carta.composable.CartaCard
import dev.rulsoft.nb22.presentation.carta.composable.FabCliente
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartaListScreen(
    onClickCarta: (Int) -> Unit,
    onTogglePin: (Int) -> Unit
){
    val cartaViewModel: CartaViewModel = koinViewModel()
    val cartas = cartaViewModel.state.cartasList
    CartaList(
        cartas = cartas,
        onClickCarta = onClickCarta,
        onTogglePin = onTogglePin
    )
}

@Composable
fun CartaList(
    cartas: List<CartaSimpleUI>,
    onClickCarta: (Int) -> Unit,
    onTogglePin: (Int) -> Unit
){
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp),
    ) {
        items(cartas) { carta ->
            CartaCard(
                carta = carta,
                onClick = {
                    onClickCarta(it)
                },
                onLongClick = {
                    onTogglePin(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun CartaListScreenPreview(){
    val cartas: MutableMap<TipoCarta, CartaUI> = mutableMapOf()
    val pilares = mutableMapOf<String, Pilar>()
    pilares["pp"] = Pilar(valor = 11, etiqueta = "11")
    pilares["ne"] = Pilar(valor = 7, etiqueta = "7")
    pilares["pd"] = Pilar(valor = 9, etiqueta = "9")
    val cartaPrincipal = CartaUI(id = 1, tipoCarta = TipoCarta.PRINCIPAL, pilares = pilares/* otros valores de la carta */)
    val cartaComplementaria = CartaUI(id = 2, tipoCarta = TipoCarta.COMPLEMENTARIA, pilares = pilares/* otros valores de la carta */)

    cartas[TipoCarta.PRINCIPAL] = cartaPrincipal
    cartas[TipoCarta.COMPLEMENTARIA] = cartaComplementaria
    val state = CartaUIState(cartas = cartas)
    CartaList(cartas = state.cartasList, onClickCarta = {}, onTogglePin = {})
}
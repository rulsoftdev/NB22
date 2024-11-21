package dev.rulsoft.nb22.presentation.carta.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rulsoft.nb22.presentation.common.utils.convertMinutesToHoursMinutes
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.presentation.carta.CartaSimpleUI
import dev.rulsoft.nb22.presentation.carta.Pilar
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartaCard(
    carta: CartaSimpleUI,
    onClick: (Int) -> Unit,
    onLongClick: (Int) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
    var isLongPressed by remember { mutableStateOf(false) }

    ElevatedCard(
        shape = RoundedCornerShape(
            topStart = 8.dp,
            bottomStart = 8.dp,
            topEnd = 8.dp,
            bottomEnd = 8.dp
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    onClick(carta.id)
                },
                onLongClick = {
                    isLongPressed = !isLongPressed
                    if (isLongPressed) {
                        onLongClick(carta.id)
                    }
                }
            )
            .padding(vertical = 8.dp)
    ) {

        val pp = carta.pilares["pp"]
        val pd = carta.pilares["pd"]
        val ne = carta.pilares["ne"]

        Row() {
            Column (modifier = Modifier
                .fillMaxWidth(.4f)
                .background(if(isLongPressed) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiaryContainer)
                .padding(16.dp)
            ){
                Text(
                    text = "PP: ${pp!!.etiqueta}",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "NE: ${ne!!.etiqueta}",
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "PD: ${pd!!.etiqueta}",
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                    ,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(carta.esPrincipal) {
                        AssistChip(
                            modifier = Modifier.height(24.dp),
                            onClick = {},
                            label = {
                                Text(
                                    text = "Multiple",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        )
                    }
                }
            }
            Column(modifier = Modifier
                .padding(16.dp)
            ){
                // Image(painter = R.drawable.noimage, contentDescription = "")
                Text(
                    text = carta.alias,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${dateFormat.format(carta.fecha)} " +
                            convertMinutesToHoursMinutes(carta.hora),
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CardCartaPreview(){
    val pilares = mutableMapOf<String, Pilar>()
    pilares["pp"] = Pilar(valor = 30, etiqueta = "30/3")
    pilares["ne"] = Pilar(valor = 26, etiqueta = "26/8")
    pilares["pd"] = Pilar(valor = 40, etiqueta = "40/4")
    val carta = CartaSimpleUI(id = 1, tipoCarta = TipoCarta.PRINCIPAL, alias = "Rul", pilares = pilares)
    CartaCard(carta = carta, onClick = {}, onLongClick = {})
}
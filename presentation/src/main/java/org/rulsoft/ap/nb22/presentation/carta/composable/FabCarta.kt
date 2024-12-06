package org.rulsoft.ap.nb22.presentation.carta.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton.FAB_SHARE
import org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton.FAB_SHOW_CARTAS
import org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton.FilterFabComponent
import org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton.FilterFabItem

@Composable
fun FabCliente(
    onAction: (String) -> Unit,
) {
    FilterFabComponent(
        items = listOf(
            FilterFabItem(
                icon = Icons.Filled.Share, label = "Compartir",
                identifier = FAB_SHARE
            ),
            FilterFabItem(
                icon = Icons.AutoMirrored.Filled.List, label = "Mis Cartas",
                identifier = FAB_SHOW_CARTAS
            )
        ),
        onClickAction = {
            when (it) {
                FAB_SHARE -> {
                    onAction(FAB_SHARE)
                }
                FAB_SHOW_CARTAS -> {
                    onAction(FAB_SHOW_CARTAS)
                }
            }
        }
    )
}
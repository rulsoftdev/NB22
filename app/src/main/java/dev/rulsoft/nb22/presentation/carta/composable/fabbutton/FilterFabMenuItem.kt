package dev.rulsoft.nb22.presentation.carta.composable.fabbutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterFabMenuItem(
    menuItem: FilterFabItem,
    onMenuItemClick: (FilterFabItem) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.padding(end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //label
        FilterFabMenuLabel(label = menuItem.label)

        //fab
        FilterFabMenuButton(item = menuItem, onClick = onMenuItemClick)

    }

}
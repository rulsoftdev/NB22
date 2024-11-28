package org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton.FilterFabItem

@Composable
fun FilterFabMenuButton(
    item: FilterFabItem,
    onClick: (FilterFabItem) -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier.size(40.dp),
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 1.dp),
        onClick = {
            onClick(item)
        },
        containerColor = MaterialTheme.colorScheme.primary,
        shape = CircleShape
    ) {
        Icon(
            item.icon,
            contentDescription = item.label,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
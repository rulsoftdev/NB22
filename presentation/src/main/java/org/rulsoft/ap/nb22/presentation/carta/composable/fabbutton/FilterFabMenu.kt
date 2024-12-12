package org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
// Función para mostrar el FAB
fun FilterFabMenu(
    visible: Boolean,
    items: List<FilterFabItem>,
    onClickAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Bottom,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        )
    }

    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Bottom,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        ) + fadeOut(
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        )
    }


    AnimatedVisibility(visible = visible, enter = enterTransition, exit = exitTransition) {
        Column(
            modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items.forEach { menuItem ->
                FilterFabMenuItem(
                    menuItem = menuItem,
                    onMenuItemClick = {
                        onClickAction(it.identifier)
                    }
                )
            }
        }
    }
}
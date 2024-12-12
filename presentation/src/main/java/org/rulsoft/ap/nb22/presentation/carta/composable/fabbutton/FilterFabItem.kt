package org.rulsoft.ap.nb22.presentation.carta.composable.fabbutton

import androidx.compose.ui.graphics.vector.ImageVector

// Clase para representar un elemento del menú del botón flotante
data class FilterFabItem (
    val icon: ImageVector,
    val label: String,
    val identifier: String
)
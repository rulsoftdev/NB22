package org.rulsoft.ap.nb22.presentation.common.navigation.composable

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun AppBarIcon(
    imageVector: ImageVector,
    @StringRes contentDescription: Int,
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(imageVector = imageVector, contentDescription = stringResource(id = contentDescription))
    }
}
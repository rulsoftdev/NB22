package org.rulsoft.ap.nb22.presentation.common.composable.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonMain(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
){
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
    ) {
        Text(text = text)
    }
}

@Composable
fun ButtonSecondary(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
){
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
    ) {
        Text(text = text)
    }
}

@Composable
fun ButtonTertiary(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
){
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
    ) {
        Text(text = text)
    }
}
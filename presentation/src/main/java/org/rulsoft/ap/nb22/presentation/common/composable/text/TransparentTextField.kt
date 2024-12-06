package org.rulsoft.ap.nb22.presentation.common.composable.text

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    textLabel: String,
    maxChar:  Int? = null,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType,
    keyboardActions: KeyboardActions,
    imeAction: ImeAction,
    trailingIcon: @Composable() (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true,
    isRequired: Boolean = false,
    colorEnabled: Boolean = false
) {
    var isError by rememberSaveable { mutableStateOf(false) }
    var isFocusActive by rememberSaveable { mutableStateOf(false) }
    var error by rememberSaveable { mutableStateOf("") }
    var label = textLabel
    var fieldValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = textFieldValue) {
        //if (textFieldValue.isNotEmpty()) {
            fieldValue.value = textFieldValue
        //}
    }
    // Log.d("TransparentTextField", "fieldValue($textLabel): ${fieldValue.value}")

    fun validateIsRequired(text: String): Boolean {
        error = "El campo $textLabel es obligatorio"
        return text.isEmpty()
    }

    fun valida(text: String) {
        var hasError = isError
        if (!isError) {
            if (isRequired)
                hasError = validateIsRequired(text)
        }
        isError = hasError
        Log.d(ContentValues.TAG, "isError: $isError")
    }

    Column {
        //TODO: Customizar los colores focus y unfocus
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                /*.onFocusChanged {
                    if (it.hasFocus) {
                        isFocusActive = true
                    }
                    if (isFocusActive && !it.isFocused) {
                        isFocusActive = false
                        valida(fieldValue.value)
                        onValueChange(fieldValue.value)
                    }
                }*/,
            value = fieldValue.value.take(maxChar ?: 40),
            onValueChange = { newValue ->
                fieldValue.value = newValue
                isError = false
                valida(fieldValue.value)
                onValueChange(fieldValue.value)
            },
            label = {
                if (isRequired)
                    label = "$textLabel *"
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                capitalization = capitalization,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = getColorDisabled(colorEnabled, MaterialTheme.colorScheme.onSurface),
                disabledTrailingIconColor = getColorDisabled(colorEnabled, MaterialTheme.colorScheme.onSurface),
                disabledPlaceholderColor = getColorDisabled(colorEnabled, MaterialTheme.colorScheme.onSurface),
                disabledTextColor = getColorDisabled(colorEnabled, MaterialTheme.colorScheme.onSurface),
                disabledBorderColor = getColorDisabled(colorEnabled, MaterialTheme.colorScheme.onSurface)
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            enabled = isEnabled,
            isError = isError
        )
        if (isError) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
        }
    }
}

fun getColorDisabled(enabled: Boolean, color: Color): Color {
    return  if(enabled)
                color
            else
                color.copy(alpha =  0.38f)
}


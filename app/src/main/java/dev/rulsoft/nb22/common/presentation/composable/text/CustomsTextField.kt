package dev.rulsoft.nb22.common.presentation.composable.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ShowAliasField(
    aliasInicial: String = "",
    focusManager: FocusManager,
    fieldChange: (String) -> Unit,
    isEnabled: Boolean = true
){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        TransparentTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldValue = aliasInicial,
            textLabel = "Alias/Nombre",
            onValueChange = {
                fieldChange(it)
            },
            keyboardType = KeyboardType.Text,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus(true)
                }
            ),
            maxChar = 30,
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done,
            isEnabled = isEnabled
        )
    }
}
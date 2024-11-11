package dev.rulsoft.nb22.presentation.carta

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.common.presentation.composable.button.ButtonMain
import dev.rulsoft.nb22.common.presentation.composable.button.ButtonSecondary
import dev.rulsoft.nb22.common.presentation.composable.picker.ShowDatePicker
import dev.rulsoft.nb22.common.presentation.composable.text.TransparentTextField
import dev.rulsoft.nb22.presentation.carta.holder.rememberPreCartaStateHolder
import org.koin.androidx.compose.koinViewModel

@Composable
fun PreCartaScreen (
    snackbarHostState: SnackbarHostState,
    onAcceder: (String) -> Unit,
    onCalcular: (String) -> Unit
){
    val vm: PreCartaViewModel = koinViewModel()
    val state by vm.state.collectAsState()
    val email by vm.email.collectAsState()
    val recordarEmail by vm.recordarEmail.collectAsState()
    val preCartaStateHolder = rememberPreCartaStateHolder(snackbarHostState)
    var emailState by remember(email) { mutableStateOf(email.ifEmpty { "" }) }
    var recordarEmailState by remember(recordarEmail) { mutableStateOf(recordarEmail) }
    var fechaState by remember { mutableStateOf("") }
    val txtFieldError = remember { mutableStateMapOf<String, String>() }
    var hasNavigated by remember { mutableStateOf(false) }

    when (state) {
        is PreCartaUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is PreCartaUIState.Success -> {
            if (!hasNavigated) {
                hasNavigated = true
                vm.actualizarCampos(emailState, recordarEmailState)
                onAcceder(emailState)
                vm.resetState() // Reinicia el estado a Idle o cualquier otro necesario
            }
        }
        is PreCartaUIState.Idle -> {
            val errorMessage = (state as? PreCartaUIState.Idle)?.errorMessage
            preCartaStateHolder.showMessageError(errorMessage)

            val context = LocalContext.current
            val errorEmailRequired = stringResource(id = R.string.errors_codigo_required)
            val errorFechaRequired = stringResource(id = R.string.errors_fecha_required)
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    text = "Calcular mi carta",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    text = "Introduce el email de cliente que te ha facilitado tú numerólogo",
                    style = MaterialTheme.typography.bodyMedium
                )
                TransparentTextField(
                    textFieldValue = emailState,
                    textLabel = "Email",
                    maxChar = 100,
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(onDone = {
                        if(emailState.isEmpty()){
                            txtFieldError["emailRequired"] = errorEmailRequired
                        } else {
                            if (emailState != "" &&
                                (emailState != email || recordarEmailState != recordarEmail)) {
                                vm.saveUsuario(emailState, recordarEmailState)
                            } else {
                                onAcceder(emailState)
                            }
                        }
                    }),
                    imeAction = ImeAction.Done,
                    onValueChange = {
                        if (emailState.isNotEmpty()) {
                            txtFieldError["emailRequired"] = ""
                        }
                        emailState = it
                    },
                    isRequired = true,
                    colorEnabled = true,
                    isEnabled = !recordarEmailState
                )
                if (txtFieldError.isNotEmpty()
                    && !txtFieldError["emailRequired"].isNullOrEmpty()
                ) {
                    Text(
                        text = txtFieldError["emailRequired"]!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Justify),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    )
                }
                Row(verticalAlignment = CenterVertically) {
                    Checkbox(
                        checked = recordarEmailState,
                        onCheckedChange = {
                            recordarEmailState = !recordarEmailState
                            if(!recordarEmailState){
                                emailState = ""
                            }
                        },
                        enabled = emailState.isNotEmpty()
                    )
                    Text(
                        modifier = Modifier,
                        text = "Recordar email",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                ButtonMain(
                    text = if(recordarEmailState && emailState == email) {
                        "Consultar mis cartas"
                    } else {
                        "Acceder"
                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    onClick = {
                        if(emailState.isEmpty()){
                            txtFieldError["emailRequired"] = errorEmailRequired
                        } else {
                            if (emailState != "" &&
                                (emailState != email || recordarEmailState != recordarEmail)) {
                                vm.saveUsuario(emailState, recordarEmailState)
                            } else {
                                onAcceder(emailState)
                            }
                        }
                    }
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    text = stringResource(id = R.string.definicion_pp),
                    style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Justify)
                )
                ShowDatePicker(
                    context = context,
                    textFieldValue = fechaState,
                    label = "Fecha nacimiento",
                    updatePicker = {
                        if(it.isNotEmpty()){
                            txtFieldError["fechaRequired"] = ""
                            fechaState = it
                        }
                    })
                if (txtFieldError.isNotEmpty()
                    && !txtFieldError["fechaRequired"].isNullOrEmpty()
                ) {
                    Text(
                        text = txtFieldError["fechaRequired"]!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    )
                }
                ButtonSecondary(
                    text = "Calcular",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    onClick = {
                        if(fechaState.isEmpty()){
                            txtFieldError["fechaRequired"] = errorFechaRequired
                        } else {
                            onCalcular(fechaState)
                        }
                    }
                )
            }
        }
    }
}

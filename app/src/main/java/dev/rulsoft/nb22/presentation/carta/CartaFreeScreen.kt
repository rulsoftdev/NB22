package dev.rulsoft.nb22.presentation.carta

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.rulsoft.nb22.R
import dev.rulsoft.nb22.presentation.common.composable.picker.ShowDatePicker
import dev.rulsoft.nb22.presentation.common.utils.onResourceReady
import dev.rulsoft.nb22.presentation.rememberWindowSize
import dev.rulsoft.nb22.presentation.carta.composable.PPCanvaFree
import dev.rulsoft.nb22.presentation.carta.composable.getEtiqueta
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
fun CartaFreeScreen (
    onSolicitar: () -> Unit = {}
){
    val windowSize = rememberWindowSize()
    val vm: NumeroViewModel = koinViewModel()
    val state = vm.state
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val captureController: CaptureController = rememberCaptureController()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Capture content
                scope.launch {
                    val bitmapAsync = captureController.captureAsync()
                    try {
                        val bitmap = bitmapAsync.await()
                        // This is captured bitmap of a content inside Capturable Composable.
                        Log.d("NB22AppState", "bitmap: $bitmap")
                        //Bitmap is captured successfully. Do something with it!
                        onResourceReady(context, bitmap.asAndroidBitmap())
                    } catch (error: Throwable) {
                        // Error occurred, do something.
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(id = R.string.fab_share)
                )
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .capturable(captureController)
                .padding(horizontal = 16.dp)
            ){
                ShowDatePicker(
                    context = context,
                    textFieldValue = state.fecha,
                    label = "Día",
                    updatePicker = { },
                    isRequired = true,
                    isMostrarEdadValue = false,
                    isReadOnly = true
                )
                PPCanvaFree(windowSize, state.madre, state.yo, state.padre, state.pp)
                OutlinedCard(
                    modifier = Modifier.padding(top = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    border = CardDefaults.outlinedCardBorder(
                        enabled = false
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "El número de tu personalidad profunda es ${getEtiqueta(state.pp)}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.resumenFree,
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                }
            }
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                text = stringResource(id = R.string.mas_info_pp),
                style = MaterialTheme.typography.labelSmall
            )
            Button(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    onSolicitar()
                }
            ) {
                Text(text = "Solicitar lectura")
            }
        }
    }
}


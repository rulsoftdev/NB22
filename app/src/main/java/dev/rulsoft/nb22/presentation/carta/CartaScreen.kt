package dev.rulsoft.nb22.presentation.carta

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.rulsoft.nb22.presentation.common.composable.picker.ShowDatePicker
import dev.rulsoft.nb22.presentation.common.composable.picker.ShowTimePicker
import dev.rulsoft.nb22.presentation.common.utils.convertDateToString
import dev.rulsoft.nb22.presentation.common.utils.convertMinutesToHoursMinutes
import dev.rulsoft.nb22.presentation.common.utils.onResourceReady
import dev.rulsoft.nb22.presentation.WindowSize
import dev.rulsoft.nb22.presentation.rememberWindowSize
import dev.rulsoft.nb22.data.types.TipoCarta
import dev.rulsoft.nb22.presentation.carta.composable.CartaCanva
import dev.rulsoft.nb22.presentation.carta.composable.FabCliente
import dev.rulsoft.nb22.presentation.carta.composable.fabbutton.FAB_SHARE
import dev.rulsoft.nb22.presentation.carta.composable.fabbutton.FAB_SHOW_CARTAS
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Locale

private const val TAG = "CartaScreen"

@Composable
fun CartaScreen(
    id: Int? = null,
    isMostrarEdadValue: Boolean,
    onShowLista: (String) -> Unit
){
    val windowSize = rememberWindowSize()
    PintarCarta(
        isMostrarEdadValue = isMostrarEdadValue,
        windowSize = windowSize,
        id = id,
        onShowLista = { email ->
            onShowLista(email)
        }
    )

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
fun PintarCarta(
    isMostrarEdadValue: Boolean,
    windowSize: WindowSize,
    id: Int? = null,
    onShowLista: (String) -> Unit
){
    val vm: CartaViewModel = koinViewModel()
    val state = vm.state
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val onEvent = vm::onEvent
    //val timeZone = TimeZone.getTimeZone("Europe/Madrid");
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"));
    //dateFormat.timeZone = timeZone;
    var fechaState by remember(state.fecha) { mutableStateOf(if(state.fecha != null) dateFormat.format(state.fecha) else "") }
    var horaState by remember(state.hora) { mutableStateOf(if(state.hora != null) convertMinutesToHoursMinutes(state.hora!!) else "") }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Principal", "Secundaria")

    val txtFieldError = remember { mutableStateMapOf<String, String>() }

    //TODO: preguntar a Jose Antonio
    val scrollState = rememberScrollState()
    val captureController = rememberCaptureController()
    //TODO: preguntar a Jose Antonio

    LaunchedEffect(state.fecha, state.hora){
        fechaState = state.fecha?.let { dateFormat.format(it) }?: ""
        horaState = convertMinutesToHoursMinutes(state.hora?: 0)
    }
    LaunchedEffect(key1 = state.hayDescripcion){
        if(state.hayDescripcion.isNotEmpty()){
            scope.launch {
                snackbarHostState.showSnackbar(
                    state.hayDescripcion,
                    withDismissAction = true,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }
    var showNotasDialog by remember { mutableStateOf(false) }
    if(showNotasDialog) {
        /*NotasDialog(
            notasState,
            setShowDialog = {
                showNotasDialog = it
            }
        ){ notas ->
            showNotasDialog = false
            //onEvent(CartaUIEvent.NotasChanged(notas, ti))
        }*/
    }

    val (heightCanvas, valores) = vm.calcularValores(windowSize)
    val (extraYLinea2, extraYLinea6, incrementoTextPosition) = valores
    Scaffold(
        floatingActionButton = {
            FabCliente(onAction = { action ->
                when(action) {
                    FAB_SHARE -> {
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
                    }
                    FAB_SHOW_CARTAS -> {
                        if(state.email != null) {
                            onShowLista(state.email)
                        }
                    }
                }

            })
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .capturable(captureController),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Row(modifier = Modifier.weight(6f)) {
                    ShowDatePicker(
                        context = context,
                        textFieldValue = fechaState,
                        label = "Día",
                        updatePicker = { },
                        isRequired = true,
                        //isReadOnly = miCarta || (id != null && id > 0),
                        isReadOnly = true,
                        isMostrarEdadValue = isMostrarEdadValue
                    )
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
                }
                Row(
                    modifier = Modifier
                        .weight(4f)
                        .padding(start = 16.dp)
                ) {
                    ShowTimePicker(
                        context = context,
                        textFieldValue = horaState,
                        label = "Hora",
                        isRequired = true,
                        //isReadOnly = miCarta || (id != null && id > 0),
                        isReadOnly = true,
                        updatePicker = { }
                    )
                    if (txtFieldError.isNotEmpty()
                        && !txtFieldError["horaRequired"].isNullOrEmpty()
                    ) {
                        Text(
                            text = txtFieldError["horaRequired"]!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                        )
                    }
                }
            }

            Column {
                if (state.multipleCartas) {
                    TabRow(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        selectedTabIndex = selectedTabIndex
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = {
                                    Text(
                                        text = convertDateToString(
                                            if (index == 0) state.cartas[TipoCarta.PRINCIPAL]!!.fecha
                                            else state.cartas[TipoCarta.COMPLEMENTARIA]!!.fecha
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (selectedTabIndex == 0)
                                MaterialTheme.colorScheme.surface
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        )
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    if (state.multipleCartas) {
                        if (selectedTabIndex == 0) {
                            CartaCanva(
                                windowSize = windowSize,
                                cartas = state.cartas,
                                onEvent = onEvent,
                                tipoCarta = TipoCarta.PRINCIPAL,
                                heightCanvas = heightCanvas,
                                extrayLinea2 = extraYLinea2,
                                extraylinea6 = extraYLinea6,
                                incrementoTextPosition = incrementoTextPosition
                            )
                        } else if (selectedTabIndex == 1) {
                            CartaCanva(
                                windowSize = windowSize,
                                cartas = state.cartas,
                                onEvent = onEvent,
                                tipoCarta = TipoCarta.COMPLEMENTARIA,
                                heightCanvas = heightCanvas,
                                extrayLinea2 = extraYLinea2,
                                extraylinea6 = extraYLinea6,
                                incrementoTextPosition = incrementoTextPosition
                            )
                        }
                    } else {
                        CartaCanva(
                            windowSize = windowSize,
                            cartas = state.cartas,
                            onEvent = onEvent,
                            tipoCarta = TipoCarta.PRINCIPAL,
                            heightCanvas = heightCanvas,
                            extrayLinea2 = extraYLinea2,
                            extraylinea6 = extraYLinea6,
                            incrementoTextPosition = incrementoTextPosition
                        )
                    }
                }

            }
        }
    }
}


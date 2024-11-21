package dev.rulsoft.nb22.presentation.common.composable.picker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import dev.rulsoft.nb22.presentation.common.composable.text.TransparentTextField
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun ShowDatePicker(
    context: Context,
    textFieldValue: String,
    label: String,
    updatePicker: (String) -> Unit,
    isReadOnly: Boolean = false,
    isRequired: Boolean = false,
    isMostrarEdadValue: Boolean = false,
    modifier: Modifier = Modifier
){
    // println(textFieldValue.value)
   // val timeZone = TimeZone.getTimeZone("Europe/Madrid");
    val sdfShort = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"));
   // sdfShort.timeZone = timeZone;
    //val sdfFull = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000Z", Locale("es", "ES"));
    //sdfFull.timeZone = timeZone;
    var year: Int
    var month: Int
    var day : Int
    var fieldValue = ""

    val calendar = Calendar.getInstance()
    if (!textFieldValue.isNullOrBlank()) {
        calendar.time = sdfShort.parse(textFieldValue) as Date;// all done
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        fieldValue = "${day.toString().padStart(2,'0')}/${(month+1).toString().padStart(2, '0')}/$year" // ????
        if(isMostrarEdadValue){
            fieldValue = "${textFieldValue} (${calcularEdad(textFieldValue)} años)"
        }
    } else {
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker,
          year: Int,
          month: Int,
          dayOfMonth: Int ->
            fieldValue = "${dayOfMonth.toString().padStart(2,'0')}/${(month+1).toString().padStart(2, '0')}/$year"
            updatePicker(fieldValue)
        }, year, month, day
    )
    val calendarMin: Calendar = Calendar.getInstance()
    calendarMin.time = sdfShort.parse("01/01/1000")!!
    datePickerDialog.datePicker.minDate = calendarMin.timeInMillis

    // Text(text = "Selected Date: ${date.value}")
    TransparentTextField(
        modifier = modifier.clickable {
            if (!isReadOnly) {
                datePickerDialog.show()
            }
        },
        textFieldValue = fieldValue,
        textLabel = label,
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
            /*onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }*/
        ),
        imeAction = ImeAction.Next,
        onValueChange = {
            updatePicker(it)
        },
        trailingIcon = {
            IconButton(onClick = {
                if (!isReadOnly) {
                    datePickerDialog.show()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.EditCalendar,
                    contentDescription = "Icon calendario $label"
                )
            }
        },
        isEnabled = false,
        colorEnabled = true,
        isRequired = isRequired
    )
}

private fun calcularEdad(textFieldValue: String): Any {
    val fechaNacimiento = Calendar.getInstance()
    //Se crea un objeto con la fecha actual
    //Se crea un objeto con la fecha actual
    val fechaActual = Calendar.getInstance()
    //Se asigna la fecha recibida a la fecha de nacimiento.
    //Se asigna la fecha recibida a la fecha de nacimiento.
    fechaNacimiento.time = SimpleDateFormat("dd/MM/yyyy").parse(textFieldValue)
    //Se restan la fecha actual y la fecha de nacimiento
    //Se restan la fecha actual y la fecha de nacimiento
    var edad = fechaActual[Calendar.YEAR] - fechaNacimiento[Calendar.YEAR]
    val mes = fechaActual[Calendar.MONTH] - fechaNacimiento[Calendar.MONTH]
    val dia = fechaActual[Calendar.DATE] - fechaNacimiento[Calendar.DATE]
    //Se ajusta el año dependiendo el mes y el día
    //Se ajusta el año dependiendo el mes y el día
    if (mes < 0 || mes == 0 && dia < 0) {
        edad--
    }
    //Regresa la edad en base a la fecha de nacimiento
    //Regresa la edad en base a la fecha de nacimiento
    return edad
}

@Composable
fun ShowTimePicker(
    context: Context,
    textFieldValue: String,
    label: String,
    updatePicker: (String) -> Unit,
    isReadOnly: Boolean = false,
    isRequired: Boolean = false,
    modifier: Modifier = Modifier
){
    // println(textFieldValue.value)
    val timeZone = TimeZone.getTimeZone("Europe/Madrid");
    val sdfShort = SimpleDateFormat("HH:mm", Locale("es", "ES"));
    sdfShort.timeZone = timeZone;

    var hora: Int
    var minuto: Int

    val calendar = Calendar.getInstance(timeZone)
    var fieldValue = ""

    if (!textFieldValue.isNullOrBlank()) {
        calendar.time = sdfShort.parse(textFieldValue) as Date;// all done
        hora = calendar.get(Calendar.HOUR_OF_DAY)
        minuto = calendar.get(Calendar.MINUTE)
        fieldValue = "${hora.toString().padStart(2,'0')}:${minuto.toString().padStart(2, '0')}"
        updatePicker(fieldValue)
    } else {
        hora = calendar.get(Calendar.HOUR_OF_DAY)
        minuto = calendar.get(Calendar.MINUTE)
    }

    val timePickerDialog = TimePickerDialog(
        context,
        {_, mHour : Int, mMinute: Int ->
            fieldValue = "${mHour.toString().padStart(2,'0')}:${mMinute.toString().padStart(2, '0')}"
        }, hora, minuto, true
    )

    // Text(text = "Selected Date: ${date.value}")
    TransparentTextField(
        modifier = modifier.clickable {
            if (!isReadOnly) {
                timePickerDialog.show()
            }
        },
        textFieldValue = fieldValue,
        textLabel = label,
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(
            /*onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }*/
        ),
        imeAction = ImeAction.Next,
        onValueChange = {
            updatePicker(it)
        },
        trailingIcon = {
            IconButton(onClick = {
                if (!isReadOnly) {
                    timePickerDialog.show()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Schedule,
                    contentDescription = "Icon calendario $label"
                )
            }
        },
        isEnabled = false,
        colorEnabled = true,
        isRequired = isRequired
    )
}
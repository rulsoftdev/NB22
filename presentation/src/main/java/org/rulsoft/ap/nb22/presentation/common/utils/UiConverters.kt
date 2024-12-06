package org.rulsoft.ap.nb22.presentation.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun convertMinutesToHoursMinutes(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return String.format("%02d:%02d", hours, remainingMinutes)
}

fun convertDateToString(fecha: Date?): String{
    if (fecha == null){
        return ""
    }
    val timeZone = TimeZone.getTimeZone("Europe/Madrid")
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
    dateFormat.timeZone = timeZone
    return dateFormat.format(fecha)
}
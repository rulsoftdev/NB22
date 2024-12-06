package org.rulsoft.ap.nb22.core_android.logger.impl

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.core.annotation.Factory
import org.rulsoft.ap.nb22.core.logger.CrashlyticsLogger

@Factory
class FirebaseCrashlyticsLogger : CrashlyticsLogger {

    override fun logEvent(eventName: String, params: Map<String, String>?) {
        if (eventName.isBlank()) {
            FirebaseCrashlytics.getInstance().log("Evento vacío ignorado.")
            Log.w("CrashlyticsLogger", "Intento de log con evento vacío ignorado.")
            return
        }

        FirebaseCrashlytics.getInstance().log("Evento: $eventName")
        Log.d("CrashlyticsLogger", "Evento registrado: $eventName")

        params?.forEach { (key, value) ->
            FirebaseCrashlytics.getInstance().setCustomKey(key, value)
            Log.d("CrashlyticsLogger", "Parámetro añadido: $key = $value")
        }
    }

    override fun logException(exception: Throwable) {
        if (exception.message?.contains("ExpectedException") == true) {
            FirebaseCrashlytics.getInstance().log("Excepción esperada no enviada a Crashlytics.")
            Log.i("CrashlyticsLogger", "Excepción esperada ignorada: ${exception.message}")
            return
        }

        FirebaseCrashlytics.getInstance().recordException(exception)
        Log.e("CrashlyticsLogger", "Excepción registrada: ${exception.message}", exception)
    }

    override fun logMessage(message: String) {
        if (message.isBlank()) {
            FirebaseCrashlytics.getInstance().log("Mensaje vacío ignorado.")
            Log.w("CrashlyticsLogger", "Intento de log con mensaje vacío ignorado.")
            return
        }

        FirebaseCrashlytics.getInstance().log(message)
        FirebaseCrashlytics.getInstance().sendUnsentReports()

        Log.d("CrashlyticsLogger", "Mensaje registrado y reportes no enviados solicitados: $message")
    }

    override fun setUserInfo(userId: String, userEmail: String?, userName: String?) {
        FirebaseCrashlytics.getInstance().setUserId(userId)
        Log.d("CrashlyticsLogger", "ID de usuario establecido: $userId")

        userEmail?.let {
            FirebaseCrashlytics.getInstance().setCustomKey("user_email", it)
            Log.d("CrashlyticsLogger", "Correo de usuario establecido: $it")
        }

        userName?.let {
            FirebaseCrashlytics.getInstance().setCustomKey("user_name", it)
            Log.d("CrashlyticsLogger", "Nombre de usuario establecido: $it")
        }
    }

    // Verifica si hay reportes pendientes de ser enviados.
    override fun checkUnsentReports() {
        FirebaseCrashlytics.getInstance().checkForUnsentReports()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val hasUnsentReports = task.result ?: false
                    Log.d("CrashlyticsLogger", "¿Hay informes pendientes? $hasUnsentReports")
                } else {
                    Log.e("CrashlyticsLogger", "Error comprobando informes pendientes", task.exception)
                }
            }
    }
}

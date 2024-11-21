package dev.rulsoft.nb22.core.logger

interface CrashlyticsLogger {
    fun logEvent(eventName: String, params: Map<String, String>?)
    fun logException(exception: Throwable)
}
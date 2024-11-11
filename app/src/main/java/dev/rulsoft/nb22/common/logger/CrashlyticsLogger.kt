package dev.rulsoft.nb22.common.logger

interface CrashlyticsLogger {
    fun logEvent(eventName: String, params: Map<String, String>?)
    fun logException(exception: Throwable)
}
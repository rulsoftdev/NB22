package org.rulsoft.ap.nb22.core.logger

interface CrashlyticsLogger {
    fun logEvent(eventName: String, params: Map<String, String>?)
    fun logException(exception: Throwable)
    fun setUserInfo(userId: String, userEmail: String? = null, userName: String? = null)
    fun logMessage(message: String)
    fun checkUnsentReports()
}
package dev.rulsoft.nb22.common.logger.impl

import com.google.firebase.crashlytics.CustomKeysAndValues
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.rulsoft.nb22.common.logger.CrashlyticsLogger

class FirebaseCrashlyticsLogger : CrashlyticsLogger {
    override fun logEvent(eventName: String, params: Map<String, String>?) {
        FirebaseCrashlytics.getInstance().log(eventName)
        params?.let {
            val customKeys = CustomKeysAndValues.Builder()
            for ((key, value) in it) {
                customKeys.putString(key, value)
            }
            FirebaseCrashlytics.getInstance().setCustomKeys(customKeys.build())
        }
    }

    override fun logException(exception: Throwable) {
        FirebaseCrashlytics.getInstance().recordException(exception)
    }
}
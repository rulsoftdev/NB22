package org.rulsoft.ap.nb22.core_android


import org.koin.dsl.module
import org.rulsoft.ap.nb22.core.logger.CrashlyticsLogger
import org.rulsoft.ap.nb22.core_android.logger.impl.FirebaseCrashlyticsLogger

val coreAndroidModule = module {
    single<CrashlyticsLogger> { FirebaseCrashlyticsLogger() }
}
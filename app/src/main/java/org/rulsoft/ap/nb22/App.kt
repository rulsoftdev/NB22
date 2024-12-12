package org.rulsoft.ap.nb22

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module
import org.rulsoft.ap.nb22.core_android.coreAndroidModule
import org.rulsoft.ap.nb22.data.dataModule
import org.rulsoft.ap.nb22.data_android.dataAndroidModule
import org.rulsoft.ap.nb22.domain.DomainModule
import org.rulsoft.ap.nb22.presentation.AppVariant
import org.rulsoft.ap.nb22.presentation.PresentationModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicialización de Firebase
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = true

        // Inicializar la variante de la aplicación
        AppVariant.current = BuildConfig.APP_VARIANT

        startKoin {
            //androidLogger(Level.DEBUG) // Esto imprime más información sobre la resolución de dependencias
            androidContext(this@App)
            modules(
                dataModule,
                dataAndroidModule,
                coreAndroidModule,
                PresentationModule().module,
                DomainModule().module,
            )
        }
    }
}
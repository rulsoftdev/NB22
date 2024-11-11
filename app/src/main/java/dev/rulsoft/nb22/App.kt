package dev.rulsoft.nb22

import android.app.Application
import androidx.room.Room
import dev.rulsoft.nb22.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // androidLogger(Level.DEBUG) // Esto imprime más información sobre la resolución de dependencias
            androidContext(this@App)
            modules(appModule)
        }
    }
}
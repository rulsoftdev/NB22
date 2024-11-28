package org.rulsoft.ap.nb22.data_android

import androidx.room.Room
import org.koin.dsl.module
import org.rulsoft.ap.nb22.data_android.carta.database.CartaLocalDataRepository
import org.rulsoft.ap.nb22.data_android.common.database.NB22Database
import org.rulsoft.ap.nb22.data_android.usuario.database.UsuarioLocalDataRepository
import org.rulsoft.ap.nb22.domain.carta.CartaLocalRepository
import org.rulsoft.ap.nb22.domain.usuario.UsuarioLocalRepository

val dataAndroidModule = module {

    // Proveer la instancia de la base de datos
    single {
        Room.databaseBuilder(
            get(), // Context
            NB22Database::class.java,
            "nb22-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // Proveedores Locales de Room
    single { get<NB22Database>().usuarioDao() }
    single { get<NB22Database>().cartaDao() }

    // Proveemos los locales Repository
    single<UsuarioLocalRepository> { UsuarioLocalDataRepository(get()) }
    single<CartaLocalRepository> { CartaLocalDataRepository(get()) }

}


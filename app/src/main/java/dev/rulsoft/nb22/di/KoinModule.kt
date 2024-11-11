package dev.rulsoft.nb22.di

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import dev.rulsoft.nb22.common.data.database.NB22Database
import dev.rulsoft.nb22.common.data.networking.ApiClientKtor
import dev.rulsoft.nb22.common.logger.CrashlyticsLogger
import dev.rulsoft.nb22.common.logger.impl.FirebaseCrashlyticsLogger
import dev.rulsoft.nb22.data.carta.database.CartaLocalDataRepository
import dev.rulsoft.nb22.data.carta.networking.CartaRemoteDataRemoteRepository
import dev.rulsoft.nb22.data.carta.networking.CartaServiceKtor
import dev.rulsoft.nb22.data.curso.networking.CursoRemoteDataRemoteRepository
import dev.rulsoft.nb22.data.curso.networking.CursoServiceKtor
import dev.rulsoft.nb22.data.numero.networking.NumeroRemoteDataRemoteRepository
import dev.rulsoft.nb22.data.numero.networking.NumeroServiceKtor
import dev.rulsoft.nb22.data.terapeuta.networking.TerapeutaRemoteDataRepository
import dev.rulsoft.nb22.data.terapeuta.networking.TerapeutaServiceKtor
import dev.rulsoft.nb22.data.usuario.database.UsuarioLocalDataRepository
import dev.rulsoft.nb22.data.usuario.networking.UsuarioRemoteDataRepository
import dev.rulsoft.nb22.data.usuario.networking.UsuarioServiceKtor
import dev.rulsoft.nb22.domain.carta.CartaLocalRepository
import dev.rulsoft.nb22.domain.carta.CartaRemoteRepository
import dev.rulsoft.nb22.domain.curso.CursoRemoteRepository
import dev.rulsoft.nb22.domain.numero.NumeroRemoteRepository
import dev.rulsoft.nb22.domain.terapeuta.TerapeutaRepository
import dev.rulsoft.nb22.domain.usuario.usecase.UserCheckAndLoadCartasUseCase
import dev.rulsoft.nb22.domain.usuario.UsuarioLocalRepository
import dev.rulsoft.nb22.domain.usuario.UsuarioRemoteRepository
import dev.rulsoft.nb22.presentation.carta.PreCartaViewModel
import dev.rulsoft.nb22.presentation.carta.CartaViewModel
import dev.rulsoft.nb22.presentation.carta.NumeroViewModel
import dev.rulsoft.nb22.presentation.curso.CursoViewModel
import dev.rulsoft.nb22.presentation.curso.CursosViewModel
import dev.rulsoft.nb22.presentation.terapeuta.TerapeutaViewModel
import dev.rulsoft.nb22.presentation.terapeuta.TerapeutasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Ktor ApiClient
    single { ApiClientKtor.httpClient }

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

    // Proporciona la implementación de LogService
    single<CrashlyticsLogger> { FirebaseCrashlyticsLogger() }

    // Proveedores Remotos de API-Ktor
    single<UsuarioServiceKtor> { UsuarioServiceKtor(get()) }
    single<CartaServiceKtor> { CartaServiceKtor(get())}
    single<CursoServiceKtor> { CursoServiceKtor(get()) }
    single<NumeroServiceKtor> { NumeroServiceKtor(get()) }
    single<TerapeutaServiceKtor> { TerapeutaServiceKtor(get()) }

    // Proveedores Locales de Room
    single { get<NB22Database>().usuarioDao() }
    single { get<NB22Database>().cartaDao() }

    // Proveemos los locales Repository
    single<UsuarioLocalRepository> { UsuarioLocalDataRepository(get()) }
    single<CartaLocalRepository> { CartaLocalDataRepository(get()) }

    // Proveemos los remote Repository
    single<UsuarioRemoteRepository> { UsuarioRemoteDataRepository(get()) }
    single<CartaRemoteRepository> { CartaRemoteDataRemoteRepository(get()) }
    single<CursoRemoteRepository> { CursoRemoteDataRemoteRepository(get()) }
    single<NumeroRemoteRepository> { NumeroRemoteDataRemoteRepository(get()) }
    single<TerapeutaRepository> { TerapeutaRemoteDataRepository(get()) }

    // Proveemos Casos de Uso
    single<UserCheckAndLoadCartasUseCase> { UserCheckAndLoadCartasUseCase(get(), get(), get(), get()) }

    // Proveer ViewModels de Carta
    viewModel { PreCartaViewModel(get(), get(), get()) }
    viewModel { (handle: SavedStateHandle) -> CartaViewModel(get(), get(), handle) }
    viewModel { (handle: SavedStateHandle) -> NumeroViewModel(get(), get(), handle) }

    // Proveer ViewModels de Curso
    viewModel { (handle: SavedStateHandle) -> CursoViewModel(get(), get(), handle) }
    viewModel { CursosViewModel(get(), get()) }

    // Proveer ViewModels de Terapeuta
    viewModel { (handle: SavedStateHandle) -> TerapeutaViewModel(get(), get(), handle) }
    viewModel { TerapeutasViewModel(get(), get()) }


}
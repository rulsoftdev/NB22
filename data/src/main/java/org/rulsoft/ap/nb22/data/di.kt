package org.rulsoft.ap.nb22.data

import org.koin.dsl.module
import org.rulsoft.ap.nb22.data.carta.networking.CartaRemoteDataRemoteRepository
import org.rulsoft.ap.nb22.data.carta.networking.CartaServiceKtor
import org.rulsoft.ap.nb22.data.common.networking.ApiClientKtor
import org.rulsoft.ap.nb22.data.common.networking.BaseServiceKtor
import org.rulsoft.ap.nb22.data.curso.networking.CursoRemoteDataRepository
import org.rulsoft.ap.nb22.data.curso.networking.CursoServiceKtor
import org.rulsoft.ap.nb22.data.numero.networking.NumeroRemoteDataRepository
import org.rulsoft.ap.nb22.data.numero.networking.NumeroServiceKtor
import org.rulsoft.ap.nb22.data.terapeuta.networking.TerapeutaRemoteDataRepository
import org.rulsoft.ap.nb22.data.terapeuta.networking.TerapeutaServiceKtor
import org.rulsoft.ap.nb22.data.usuario.networking.UsuarioRemoteDataRepository
import org.rulsoft.ap.nb22.data.usuario.networking.UsuarioServiceKtor
import org.rulsoft.ap.nb22.domain.carta.CartaRemoteRepository
import org.rulsoft.ap.nb22.domain.curso.CursoRemoteRepository
import org.rulsoft.ap.nb22.domain.numero.NumeroRemoteRepository
import org.rulsoft.ap.nb22.domain.terapeuta.TerapeutaRepository
import org.rulsoft.ap.nb22.domain.usuario.UsuarioRemoteRepository

val dataModule = module {
    // Ktor ApiClient
    single { ApiClientKtor }

    single<BaseServiceKtor> { BaseServiceKtor(get()) }
    // Proveedores Remotos de API-Ktor
    single<UsuarioServiceKtor> { UsuarioServiceKtor(get()) }
    single<CartaServiceKtor> { CartaServiceKtor(get())}
    single<CursoServiceKtor> { CursoServiceKtor(get()) }
    single<NumeroServiceKtor> { NumeroServiceKtor(get()) }
    single<TerapeutaServiceKtor> { TerapeutaServiceKtor(get()) }

    // Proveemos los remote Repository
    single<UsuarioRemoteRepository> { UsuarioRemoteDataRepository(get()) }
    single<CartaRemoteRepository> { CartaRemoteDataRemoteRepository(get()) }
    single<CursoRemoteRepository> { CursoRemoteDataRepository(get()) }
    single<NumeroRemoteRepository> { NumeroRemoteDataRepository(get()) }
    single<TerapeutaRepository> { TerapeutaRemoteDataRepository(get()) }
}
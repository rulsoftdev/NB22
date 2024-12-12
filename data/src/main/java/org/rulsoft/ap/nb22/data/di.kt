package org.rulsoft.ap.nb22.data

import org.koin.dsl.module
import org.rulsoft.ap.nb22.data.carta.networking.CartaRemoteDataRepository
import org.rulsoft.ap.nb22.data.carta.networking.CartaServiceKtor
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
    single<BaseServiceKtor> { BaseServiceKtor() }

    // Proveedores Remotos de API-Ktor
    single<UsuarioServiceKtor> { UsuarioServiceKtor() }
    single<CartaServiceKtor> { CartaServiceKtor()}
    single<CursoServiceKtor> { CursoServiceKtor() }
    single<NumeroServiceKtor> { NumeroServiceKtor() }
    single<TerapeutaServiceKtor> { TerapeutaServiceKtor() }

    // Proveemos los remote Repository
    single<UsuarioRemoteRepository> { UsuarioRemoteDataRepository(get()) }
    single<CartaRemoteRepository> { CartaRemoteDataRepository(get()) }
    single<CursoRemoteRepository> { CursoRemoteDataRepository(get()) }
    single<NumeroRemoteRepository> { NumeroRemoteDataRepository(get()) }
    single<TerapeutaRepository> { TerapeutaRemoteDataRepository(get()) }
}
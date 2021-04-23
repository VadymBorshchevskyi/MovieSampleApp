package com.borshchevskyi.data.di

import com.borshchevskyi.data.server.MovieServer
import com.borshchevskyi.domaine.di.KoinModuleProvider
import com.borshchevskyi.domaine.repository.MovieRepository
import org.koin.dsl.module

object RepositoryModule : KoinModuleProvider {
    override fun get() = module {
        single<MovieRepository> { MovieServer(get()) }
    }
}
package com.borshchevskyi.moviesampleapp

import android.app.Application
import com.borshchevskyi.data.di.NetworkModule
import com.borshchevskyi.data.di.RepositoryModule
import com.borshchevskyi.moviesampleapp.di.VMModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                    VMModule.get(),
                    NetworkModule.get(),
                    RepositoryModule.get()
            )
        }
    }
}
package com.borshchevskyi.moviesampleapp.di

import com.borshchevskyi.domaine.di.KoinModuleProvider
import com.borshchevskyi.moviesampleapp.ui.viewModel.DetailVM
import com.borshchevskyi.moviesampleapp.ui.viewModel.HomeVM
import com.borshchevskyi.moviesampleapp.ui.viewModel.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VMModule : KoinModuleProvider {
    override fun get() = module {
        viewModel { MainVM() }
        viewModel { HomeVM(get()) }
        viewModel { DetailVM(get()) }
    }
}
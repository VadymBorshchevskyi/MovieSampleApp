package com.borshchevskyi.domaine.di

import org.koin.core.module.Module

interface KoinModuleProvider {
    fun get(): Module
}
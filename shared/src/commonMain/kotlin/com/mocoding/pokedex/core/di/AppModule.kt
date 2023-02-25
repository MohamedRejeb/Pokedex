package com.mocoding.pokedex.core.di

import com.mocoding.pokedex.core.database.databaseModule
import com.mocoding.pokedex.core.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(databaseModule)
        modules(networkModule)
    }
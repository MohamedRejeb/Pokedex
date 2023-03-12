package com.mocoding.pokedex.core.database.di

import com.mocoding.pokedex.core.database.createDatabase
import com.mocoding.pokedex.core.database.dao.PokemonDao
import com.mocoding.pokedex.core.database.dao.PokemonInfoDao
import com.mocoding.pokedex.core.database.sqlDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    factory { sqlDriverFactory() }
    single { createDatabase(driver = get()) }
    single { PokemonDao(pokemonDatabase = get()) }
    single { PokemonInfoDao(pokemonDatabase = get()) }
}
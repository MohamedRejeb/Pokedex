package com.mocoding.pokedex.data.di

import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.data.repository.PokemonRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf<PokemonRepository> { PokemonRepositoryImpl() }
}
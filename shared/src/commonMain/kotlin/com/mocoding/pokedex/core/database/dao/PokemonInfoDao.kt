package com.mocoding.pokedex.core.database.dao

import com.mocoding.pokedex.core.database.PokemonDatabase
import com.mocoding.pokedex.pokedexDispatchers
import commocodingpokedex.PokemonEntity
import commocodingpokedex.PokemonInfoEntity
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonInfoDao: KoinComponent {
    private val pokemonDatabase by inject<PokemonDatabase>()
    private val query get() = pokemonDatabase.pokemonInfoEntityQueries

    suspend fun selectOneByName(name: String) = withContext(pokedexDispatchers.io) {
        query.selectOneByName(name = name).executeAsOneOrNull()
    }

    suspend fun insert(pokemonInfoEntity: PokemonInfoEntity) = withContext(pokedexDispatchers.io) {
        query.insert(pokemonInfoEntity)
    }
}
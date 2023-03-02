package com.mocoding.pokedex.core.database.dao

import com.mocoding.pokedex.core.database.PokemonDatabase
import com.mocoding.pokedex.pokedexDispatchers
import commocodingpokedex.PokemonEntity
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonDao: KoinComponent {
    private val pokemonDatabase by inject<PokemonDatabase>()
    private val query get() = pokemonDatabase.pokemonEntityQueries

    suspend fun selectAllByPage(page: Long) = withContext(pokedexDispatchers.io) {
        query.selectAllByPage(page = page).executeAsList()
    }

    suspend fun insert(pokemonEntity: PokemonEntity) = withContext(pokedexDispatchers.io) {
        query.insert(pokemonEntity)
    }
}
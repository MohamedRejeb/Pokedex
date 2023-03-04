package com.mocoding.pokedex.core.database.dao

import com.mocoding.pokedex.core.database.PokemonDatabase
import com.mocoding.pokedex.pokedexDispatchers
import commocodingpokedex.PokemonInfoEntity
import kotlinx.coroutines.withContext

class PokemonInfoDao(
    private val pokemonDatabase: PokemonDatabase
) {
    private val query get() = pokemonDatabase.pokemonInfoEntityQueries

    suspend fun selectOneByName(name: String) = withContext(pokedexDispatchers.io) {
        query.selectOneByName(name = name).executeAsOneOrNull()
    }

    suspend fun insert(pokemonInfoEntity: PokemonInfoEntity) = withContext(pokedexDispatchers.io) {
        query.insert(pokemonInfoEntity)
    }
}
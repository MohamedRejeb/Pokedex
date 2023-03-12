package com.mocoding.pokedex.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import com.mocoding.pokedex.core.database.PokemonDatabase
import com.mocoding.pokedex.pokedexDispatchers
import commocodingpokedex.PokemonInfoEntity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PokemonInfoDao(
    private val pokemonDatabase: PokemonDatabase
) {
    private val query get() = pokemonDatabase.pokemonInfoEntityQueries

    suspend fun selectOneByName(name: String) = withContext(pokedexDispatchers.io) {
        query.selectOneByName(name = name).executeAsOneOrNull()
    }

    suspend fun selectAllFavorite() = withContext(pokedexDispatchers.io) {
        query.selectAllFavorite().asFlow().map { it.executeAsList() }
    }

    suspend fun insert(pokemonInfoEntity: PokemonInfoEntity) = withContext(pokedexDispatchers.io) {
        query.insert(pokemonInfoEntity)
    }

    suspend fun updateIsFavorite(name: String, isFavorite: Boolean) = withContext(pokedexDispatchers.io) {
        query.updateIsFavorite(
            isFavorite = if (isFavorite) 1 else 0,
            name = name
        )
    }
}
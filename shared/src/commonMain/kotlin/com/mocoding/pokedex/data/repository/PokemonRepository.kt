package com.mocoding.pokedex.data.repository

import com.mocoding.pokedex.core.model.Pokemon
import com.mocoding.pokedex.core.model.PokemonInfo

interface PokemonRepository {

    suspend fun getPokemonList(page: Long): Result<List<Pokemon>>

    suspend fun getPokemonByName(name: String): Result<PokemonInfo>
    suspend fun getFavoritePokemonList(): List<Pokemon>
    suspend fun updatePokemonFavoriteState(name: String, isFavorite: Boolean)

}
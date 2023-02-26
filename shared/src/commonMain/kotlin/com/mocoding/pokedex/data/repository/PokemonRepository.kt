package com.mocoding.pokedex.data.repository

import com.mocoding.pokedex.core.model.Pokemon
import com.mocoding.pokedex.core.model.PokemonInfo

interface PokemonRepository {

    suspend fun getPokemonList(page: Int): List<Pokemon>

    suspend fun getPokemonByName(name: String): PokemonInfo

}
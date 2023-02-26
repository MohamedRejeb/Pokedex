package com.mocoding.pokedex.data.repository

import com.mocoding.pokedex.core.model.Pokemon
import com.mocoding.pokedex.core.model.PokemonInfo
import com.mocoding.pokedex.core.network.client.PokemonClient
import com.mocoding.pokedex.pokedexDispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonRepositoryImpl: PokemonRepository, KoinComponent {

    private val pokemonClient by inject<PokemonClient>()

    override suspend fun getPokemonList(page: Int): List<Pokemon> =
        withContext(pokedexDispatchers.io) {
            pokemonClient.getPokemonList(page = page).results
        }

    override suspend fun getPokemonByName(name: String): PokemonInfo =
        withContext(pokedexDispatchers.io) {
            pokemonClient.getPokemonByName(name = name)
        }

}
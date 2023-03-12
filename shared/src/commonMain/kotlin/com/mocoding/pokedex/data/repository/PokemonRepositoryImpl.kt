package com.mocoding.pokedex.data.repository

import com.mocoding.pokedex.core.database.dao.PokemonDao
import com.mocoding.pokedex.core.database.dao.PokemonInfoDao
import com.mocoding.pokedex.core.model.Pokemon
import com.mocoding.pokedex.core.model.PokemonInfo
import com.mocoding.pokedex.core.network.client.PokemonClient
import com.mocoding.pokedex.data.toPokemon
import com.mocoding.pokedex.data.toPokemonEntity
import com.mocoding.pokedex.data.toPokemonInfo
import com.mocoding.pokedex.data.toPokemonInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonRepositoryImpl: PokemonRepository, KoinComponent {

    private val pokemonClient by inject<PokemonClient>()
    private val pokemonDao by inject<PokemonDao>()
    private val pokemonInfoDao by inject<PokemonInfoDao>()

    override suspend fun getPokemonList(page: Long): Result<List<Pokemon>> {
        return try {
            val cachedPokemonList = pokemonDao.selectAllByPage(page)

            if (cachedPokemonList.isEmpty()) {
                val response = pokemonClient.getPokemonList(page = page)
                response.results.forEach { pokemon ->
                    pokemonDao.insert(pokemon.toPokemonEntity(page))
                }

                Result.success(pokemonDao.selectAllByPage(page).map { it.toPokemon() })
            } else {
                Result.success(cachedPokemonList.map { it.toPokemon() })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getPokemonFlowByName(name: String): Result<PokemonInfo> {
        return try {
            val cachedPokemon = pokemonInfoDao.selectOneByName(name = name)

            if (cachedPokemon == null) {
                val response = pokemonClient.getPokemonByName(name = name)
                pokemonInfoDao.insert(response.toPokemonInfoEntity())

                Result.success(response)
            } else {
                Result.success(cachedPokemon.toPokemonInfo())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFavoritePokemonListFlow(): Flow<List<Pokemon>> {
        return pokemonInfoDao.selectAllFavorite().map { list ->
            list.map { it.toPokemon() }
        }
    }

    override suspend fun updatePokemonFavoriteState(name: String, isFavorite: Boolean) {
        pokemonInfoDao.updateIsFavorite(
            name = name,
            isFavorite = isFavorite,
        )
    }

}
package com.mocoding.pokedex.core.network.model

import com.mocoding.pokedex.core.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<Pokemon>
)

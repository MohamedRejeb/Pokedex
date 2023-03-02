package com.mocoding.pokedex.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
  var page: Long = 0,
  val name: String,
  val url: String
) {

  val imageUrl get(): String {
    val index = url.split("/".toRegex()).dropLast(1).last()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
  }
}

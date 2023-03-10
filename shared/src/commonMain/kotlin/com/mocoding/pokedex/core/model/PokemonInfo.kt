package com.mocoding.pokedex.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class PokemonInfo(
  val id: Long,
  val name: String,
  val height: Long,
  val weight: Long,
  @SerialName("base_experience") val experience: Long,
  val types: List<TypeResponse>,
  val hp: Int = Random.nextInt(maxHp),
  val attack: Int = Random.nextInt(maxAttack),
  val defense: Int = Random.nextInt(maxDefense),
  val speed: Int = Random.nextInt(maxSpeed),
  val exp: Int = Random.nextInt(maxExp),
  val isFavorite: Boolean = false
) {
  val imageUrl: String =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

  @Serializable
  data class TypeResponse(
    val slot: Int,
    val type: Type
  )

  @Serializable
  data class Type(
    val name: String
  )

  companion object {
    const val maxHp = 300
    const val maxAttack = 300
    const val maxDefense = 300
    const val maxSpeed = 300
    const val maxExp = 1000
  }
}

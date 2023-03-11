package com.mocoding.pokedex.ui.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.PokemonInfo

@Composable
internal fun PokemonStats(
    pokemonInfo: PokemonInfo,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        pokemonInfo.stats.forEach { statResponse ->
            key(statResponse.stat.name) {
                PokemonStatItem(
                    statResponse = statResponse,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
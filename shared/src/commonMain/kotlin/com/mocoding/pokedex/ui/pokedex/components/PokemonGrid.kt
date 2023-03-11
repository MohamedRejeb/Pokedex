package com.mocoding.pokedex.ui.pokedex.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Pokemon

@Composable
internal fun PokemonGrid(
    onPokemonClicked: (name: String) -> Unit,
    pokemonList: List<Pokemon>,
    isLoading: Boolean,
    loadMoreItems: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = .1f,
        targetValue = .4f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(20.dp),
        modifier = modifier,
    ) {
        items(pokemonList, key = { it.name }) { pokemon ->
            PokemonItem(
                onClick = { onPokemonClicked(pokemon.name) },
                pokemon = pokemon,
                modifier = Modifier.fillMaxWidth()
            )
        }

        items(5) { index ->
            LaunchedEffect(Unit) {
                if (index == 0) loadMoreItems()
            }

            PokemonLoadingItem(alpha = alpha)
        }

    }
}
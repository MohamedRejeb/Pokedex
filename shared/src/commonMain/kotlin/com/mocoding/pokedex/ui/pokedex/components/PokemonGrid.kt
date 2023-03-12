package com.mocoding.pokedex.ui.pokedex.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
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
    loadMoreItems: () -> Unit = {},
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

    BoxWithConstraints {
        val columns = when(maxWidth) {
            in 0.dp..349.dp -> 1
            in 350.dp..599.dp -> 2
            in 600.dp..899.dp -> 3
            in 900.dp..1199.dp -> 4
            else -> 5
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
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

            if (isLoading) {
                items(5) { index ->
                    LaunchedEffect(Unit) {
                        if (index == 0) loadMoreItems()
                    }

                    PokemonLoadingItem(alpha = alpha)
                }
            }

        }
    }
}
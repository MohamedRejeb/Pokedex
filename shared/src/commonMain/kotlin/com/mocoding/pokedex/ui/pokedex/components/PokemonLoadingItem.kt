package com.mocoding.pokedex.ui.pokedex.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random

@Composable
internal fun PokemonLoadingItem(
    modifier: Modifier = Modifier,
    alpha: Float,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                this.alpha = abs(1f - alpha)
            }
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primary.copy(.6f))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .fillMaxHeight()
                    .graphicsLayer {
                        this.alpha = alpha
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onBackground.copy(.4f))
            )

            val firstBoxWidthFraction = remember {
                Random.nextFloat()
            }
            Box(
                modifier = modifier
                    .fillMaxWidth(max(.3f, firstBoxWidthFraction))
                    .height(20.dp)
                    .graphicsLayer {
                        this.alpha = alpha
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onBackground.copy(.4f))
            )

            val secondBoxWidthFraction = remember {
                Random.nextFloat()
            }
            Box(
                modifier = modifier
                    .fillMaxWidth(max(.3f, secondBoxWidthFraction))
                    .height(20.dp)
                    .graphicsLayer {
                        this.alpha = alpha
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onBackground.copy(.4f))
            )
        }
    }
}
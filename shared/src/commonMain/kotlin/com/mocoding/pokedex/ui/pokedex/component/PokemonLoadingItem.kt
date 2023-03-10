package com.mocoding.pokedex.ui.pokedex.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Pokemon
import com.seiko.imageloader.ImageRequestState
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.rememberAsyncImagePainter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

@Composable
internal fun PokemonLoadingItem(
    modifier: Modifier = Modifier,
    alpha: () -> Float,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                this.alpha = abs(1f - alpha())
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
                        this.alpha = alpha()
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onBackground.copy(.4f))
            )

            Box(
                modifier = modifier
                    .fillMaxWidth(max(.3f, Random.nextFloat()))
                    .height(20.dp)
                    .graphicsLayer {
                        this.alpha = alpha()
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onBackground.copy(.4f))
            )
            Box(
                modifier = modifier
                    .fillMaxWidth(max(.3f, Random.nextFloat()))
                    .height(20.dp)
                    .graphicsLayer {
                        this.alpha = alpha()
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onBackground.copy(.4f))
            )
        }
    }
}
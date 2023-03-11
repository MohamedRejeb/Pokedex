package com.mocoding.pokedex.ui.details.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.PokemonInfo
import com.mocoding.pokedex.ui.theme.Green300
import com.mocoding.pokedex.ui.theme.Yellow400
import kotlin.math.roundToInt

@Composable
internal fun PokemonStatItem(
    statResponse: PokemonInfo.StatsResponse,
    modifier: Modifier = Modifier
) {
    val animationProgress = remember {
        Animatable(
            initialValue = 0f,
        )
    }

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 8 * statResponse.value,
                easing = LinearEasing
            )
        )
    }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = statResponse.name,
            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(.3f)
        )

        Text(
            text = "${(statResponse.value * animationProgress.value).roundToInt()}",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(.2f)
        )

        val progress = statResponse.value.toFloat() / statResponse.maxValue.toFloat()
        val animatedProgress = progress * animationProgress.value

        val progressColor = if (progress >= .5f) Green300 else Yellow400
        val progressTrackColor = MaterialTheme.colorScheme.outline.copy(.2f)

        Box(
            modifier = Modifier
                .weight(.5f)
                .height(10.dp)
                .drawBehind {
                    drawRoundRect(
                        color = progressTrackColor,
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(size.height, size.height),
                    )

                    drawRoundRect(
                        color = progressColor,
                        topLeft = Offset.Zero,
                        size = Size(width = size.width * animatedProgress, height = size.height),
                        cornerRadius = CornerRadius(size.height, size.height),
                    )
                }
        )
    }
}
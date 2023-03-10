package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.ui.main.state.CategoryState
import com.mocoding.pokedex.ui.theme.Black
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun CategoryButton(
    onClick: () -> Unit,
    categoryState: CategoryState,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .background(
                Brush.linearGradient(
                    listOf(categoryState.startColor, categoryState.endColor)
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = categoryState.title,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(Modifier.weight(1f))

        val painter = rememberAsyncImagePainter(categoryState.iconUrl)

        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .scale(1f, .5f)
                    .offset(y = 40.dp)
                    .size(40.dp)
                    .background(
                        Brush.radialGradient(
                            listOf(
                                Black.copy(alpha = .3f),
                                Color.Transparent
                            ),
                        )
                    )
            )

            Image(
                painter = painter,
                contentDescription = categoryState.title,
                modifier = Modifier
                    .offset(y = (-8).dp)
                    .size(40.dp)
            )
        }
    }
}
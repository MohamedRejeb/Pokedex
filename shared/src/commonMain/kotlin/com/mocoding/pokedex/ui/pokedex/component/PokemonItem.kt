package com.mocoding.pokedex.ui.pokedex.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Pokemon
import com.seiko.imageloader.ImageRequestState
import com.seiko.imageloader.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PokemonItem(
    onClick: () -> Unit,
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(pokemon.imageUrl)

    ElevatedCard(
        onClick = onClick,
        //border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .fillMaxHeight()
                        .scale(1.2f)
                        .blur(16.dp)
                        .alpha(.4f)
                )

                Image(
                    painter = painter,
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .fillMaxHeight()
                )

//                if (painter.requestState is ImageRequestState.Loading
//                    || painter.requestState is ImageRequestState.Failure) {
//                    CircularProgressIndicator(
//                        color = MaterialTheme.colorScheme.onSecondary
//                    )
//                }
            }

            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
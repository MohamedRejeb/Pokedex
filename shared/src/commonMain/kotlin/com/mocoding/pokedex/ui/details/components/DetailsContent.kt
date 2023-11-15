package com.mocoding.pokedex.ui.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.ui.main.components.AsyncImage
import com.mocoding.pokedex.ui.details.DetailsComponent
import com.mocoding.pokedex.ui.details.store.DetailsStore
import com.mocoding.pokedex.ui.helper.LocalSafeArea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsContent(
    state: DetailsStore.State,
    onEvent: (DetailsStore.Intent) -> Unit,
    onOutput: (DetailsComponent.Output) -> Unit,
) {
    Box(contentAlignment = Alignment.TopCenter) {
        state.pokemonInfo?.let { pokemonInfo ->
            AsyncImage(
                url = pokemonInfo.imageUrl,
                contentDescription = pokemonInfo.name,
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(3f) }),
                modifier = Modifier
                    .widthIn(max = 800.dp)
                    .fillMaxWidth(.9f)
                    .wrapContentHeight(Alignment.Top, true)
                    .scale(1f, 1.8f)
                    .blur(70.dp, BlurredEdgeTreatment.Unbounded)
                    .alpha(.5f)
            )
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = { onOutput(DetailsComponent.Output.NavigateBack) }
                        ) {
                            Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                        }
                    },
                    title = {
                        state.pokemonInfo?.let { pokemonInfo ->
                            Text(
                                text = pokemonInfo.name.replaceFirstChar { it.uppercaseChar() },
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                state.pokemonInfo?.let { pokemonInfo ->
                                    onEvent(
                                        DetailsStore.Intent.UpdatePokemonFavoriteState(
                                            isFavorite = !pokemonInfo.isFavorite
                                        )
                                    )
                                }
                            }
                        ) {
                            Icon(
                                if (state.pokemonInfo?.isFavorite == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (state.pokemonInfo?.isFavorite == true) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            containerColor = Color.Transparent,
            modifier = Modifier.padding(LocalSafeArea.current)
        ) {  paddingValue ->
            Box(
                modifier = Modifier
                    .padding(paddingValue)
                    .padding(20.dp)
            ) {
                if (state.isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.error?.let { error ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = error)
                    }
                }

                state.pokemonInfo?.let { pokemonInfo ->
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item("image") {
                            AsyncImage(
                                url = pokemonInfo.imageUrl,
                                contentDescription = pokemonInfo.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .widthIn(max = 500.dp)
                                    .fillMaxWidth()
                                    .aspectRatio(1.2f)
                                    .fillMaxHeight()
                            )
                        }

                        item("name") {
                            Text(
                                text = pokemonInfo.name.replaceFirstChar { it.uppercaseChar() },
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.displaySmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item("id") {
                            Text(
                                text = pokemonInfo.idString,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item("abilities") {
                            AbilityRow(
                                types = pokemonInfo.types
                            )
                        }

                        item("infos") {
                            PokemonInfos(
                                pokemonInfo = pokemonInfo,
                                modifier = Modifier
                                    .padding(top = 18.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }

                        item("stats") {
                            PokemonStats(
                                pokemonInfo = pokemonInfo,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Video
import com.mocoding.pokedex.ui.main.MainComponent
import com.mocoding.pokedex.ui.main.state.CategoryState
import com.mocoding.pokedex.ui.main.store.MainStore

@Composable
internal fun MainContent(
    state: MainStore.State,
    onEvent: (MainStore.Intent) -> Unit,
    onOutput: (MainComponent.Output) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "What Pokemon are you looking for ?",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
        )

        val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f)
        TextField(
            value = state.search,
            onValueChange = { onEvent(MainStore.Intent.InputPokemonSearch(it)) },
            placeholder = {
                Text(text = "Search Pokemon")
            },
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(Icons.Rounded.Search, contentDescription = "Search Pokemon")
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            CategoryButton(
                onClick = {
                    onOutput(MainComponent.Output.PokedexClicked)
                },
                categoryState = CategoryState.pokedex,
                modifier = Modifier.weight(1f),
            )

            CategoryButton(
                onClick = {
                    onOutput(MainComponent.Output.ComingSoon)
                },
                categoryState = CategoryState.moves,
                modifier = Modifier.weight(1f),
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            CategoryButton(
                onClick = {
                    onOutput(MainComponent.Output.ComingSoon)
                },
                categoryState = CategoryState.evolutions,
                modifier = Modifier.weight(1f),
            )

            CategoryButton(
                onClick = {
                    onOutput(MainComponent.Output.ComingSoon)
                },
                categoryState = CategoryState.locations,
                modifier = Modifier.weight(1f),
            )
        }

        Text(
            text = "Watch",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 6.dp)
        )

        Divider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = .4f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        key(Video.demoList) {
            VideoRow(
                videoList = Video.demoList,
                onVideoClicked = {
                    onOutput(MainComponent.Output.ComingSoon)
                }
            )
        }

    }
}
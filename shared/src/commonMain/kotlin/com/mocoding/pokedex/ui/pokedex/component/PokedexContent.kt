package com.mocoding.pokedex.ui.pokedex.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Video
import com.mocoding.pokedex.ui.main.MainComponent
import com.mocoding.pokedex.ui.main.components.CategoryButton
import com.mocoding.pokedex.ui.main.components.VideoRow
import com.mocoding.pokedex.ui.main.store.MainStore
import com.mocoding.pokedex.ui.pokedex.PokedexComponent
import com.mocoding.pokedex.ui.pokedex.store.PokedexStore
import com.mocoding.pokedex.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PokedexContent(
    state: PokedexStore.State,
    onEvent: (PokedexStore.Intent) -> Unit,
    onOutput: (PokedexComponent.Output) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(Icons.Rounded.Menu, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) {  paddingValue ->
        Box(
            modifier = Modifier
                .padding(paddingValue)
        ) {
//            if (state.isLoading) {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    CircularProgressIndicator()
//                }
//            }

//            TextField(
//                value = state.search,
//                onValueChange = { newSearch ->
//                    onEvent(MainStore.Intent.InputPokemonSearch(newSearch))
//                },
//                placeholder = {
//                    Text(text = "Search Pokemon")
//                },
//                leadingIcon = {
//                    IconButton(
//                        onClick = {}
//                    ) {
//                        Icon(Icons.Rounded.Search, contentDescription = "Search Pokemon")
//                    }
//                },
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f),
//                    placeholderColor = MaterialTheme.colorScheme.surface,
//                    focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
//                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                ),
//                shape = MaterialTheme.shapes.extraLarge,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 20.dp)
//            )

            Text(
                text = "Pokedex",
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

            state.error?.let { error ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = error)
                }
            }

            PokemonGrid(
                onPokemonClicked = { name ->
                    onOutput(PokedexComponent.Output.PokemonClicked(name = name))
                },
                pokemonList = state.pokemonList,
                loadMoreItems = {
                    if (state.pokemonList.isEmpty()) return@PokemonGrid

                    val nextPage = state.pokemonList.last().page + 1
                    onEvent(PokedexStore.Intent.LoadPokemonListByPage(page = nextPage))
                }
            )

        }
    }
}
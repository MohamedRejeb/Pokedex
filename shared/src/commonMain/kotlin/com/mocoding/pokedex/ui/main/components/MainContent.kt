package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Video
import com.mocoding.pokedex.ui.main.MainComponent
import com.mocoding.pokedex.ui.main.store.MainStore
import com.mocoding.pokedex.ui.theme.*
import com.seiko.imageloader.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContent(
    state: MainStore.State,
    onEvent: (MainStore.Intent) -> Unit,
    onOutput: (MainComponent.Output) -> Unit,
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
        Column(
            modifier = Modifier
                .padding(paddingValue)
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

            TextField(
                value = state.search,
                onValueChange = { newSearch ->
                    onEvent(MainStore.Intent.InputPokemonSearch(newSearch))
                },
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
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f),
                    placeholderColor = MaterialTheme.colorScheme.surface,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
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
                    title = "Pokedex",
                    startColor = Red300,
                    endColor = Red500,
                    iconUrl = "https://cdn-icons-png.flaticon.com/512/361/361998.png",
                    modifier = Modifier.weight(1f)
                )

                CategoryButton(
                    onClick = {},
                    title = "Moves",
                    startColor = Yellow300,
                    endColor = Yellow500,
                    iconUrl = "https://cdn-icons-png.flaticon.com/512/616/616494.png",
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                CategoryButton(
                    onClick = {},
                    title = "Evolutions",
                    startColor = Green300,
                    endColor = Green500,
                    iconUrl = "https://cdn-icons-png.flaticon.com/512/9077/9077327.png",
                    modifier = Modifier.weight(1f)
                )

                CategoryButton(
                    onClick = {},
                    title = "Locations",
                    startColor = Blue300,
                    endColor = Blue500,
                    iconUrl = "https://cdn-icons-png.flaticon.com/512/149/149060.png",
                    modifier = Modifier.weight(1f)
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

            VideoRow(
                videoList = Video.demoList,
                onVideoClicked = {}
            )

        }
    }
}
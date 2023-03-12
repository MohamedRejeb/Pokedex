package com.mocoding.pokedex.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.ui.helper.LocalSafeArea
import com.mocoding.pokedex.ui.main.components.MainContent
import com.mocoding.pokedex.ui.main.components.MainModalDrawerSheet
import com.mocoding.pokedex.ui.main.store.MainStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(component: MainComponent) {

    val state by component.state.collectAsState()

    val items = listOf("Home" to Icons.Outlined.Home, "Favorite" to Icons.Outlined.Favorite)
    var selectedItem by remember { mutableStateOf(items[0]) }

    LaunchedEffect(selectedItem) {
        if (selectedItem.first == "Favorite") {
            component.onOutput(MainComponent.Output.FavoriteClicked)
        }
    }

    BoxWithConstraints {
        if (maxWidth > 1199.dp) {
            MainContentLarge(
                state = state,
                onEvent = component::onEvent,
                onOutput = component::onOutput,
                items = items,
                selectedItem = selectedItem,
                updateSelectedItem = { selectedItem = it }
            )
        } else {
            MainContentDefault(
                state = state,
                onEvent = component::onEvent,
                onOutput = component::onOutput,
                items = items,
                selectedItem = selectedItem,
                updateSelectedItem = { selectedItem = it }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContentDefault(
    state: MainStore.State,
    onEvent: (MainStore.Intent) -> Unit,
    onOutput: (MainComponent.Output) -> Unit,
    items: List<Pair<String, ImageVector>>,
    selectedItem: Pair<String, ImageVector>,
    updateSelectedItem: (Pair<String, ImageVector>) -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainModalDrawerSheet(
                items = items,
                selectedItem = selectedItem,
                onItemsClick = { item ->
                    scope.launch { drawerState.close() }
                    updateSelectedItem(item)
                }
            )
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() }
                                },
                            ) {
                                Icon(Icons.Rounded.Menu, contentDescription = null)
                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )
                },
                modifier = Modifier.padding(LocalSafeArea.current)
            ) { paddingValues ->
                MainContent(
                    state = state,
                    onEvent = onEvent,
                    onOutput = onOutput,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContentLarge(
    state: MainStore.State,
    onEvent: (MainStore.Intent) -> Unit,
    onOutput: (MainComponent.Output) -> Unit,
    items: List<Pair<String, ImageVector>>,
    selectedItem: Pair<String, ImageVector>,
    updateSelectedItem: (Pair<String, ImageVector>) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        MainModalDrawerSheet(
            items = items,
            selectedItem = selectedItem,
            onItemsClick = { item ->
                updateSelectedItem(item)
            }
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            modifier = Modifier.padding(LocalSafeArea.current)
        ) { paddingValues ->
            MainContent(
                state = state,
                onEvent = onEvent,
                onOutput = onOutput,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}
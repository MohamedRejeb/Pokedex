package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.ui.helper.LocalSafeArea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainModalDrawerSheet(
    items: List<Pair<String, ImageVector>>,
    selectedItem: Pair<String, ImageVector>,
    onItemsClick: (Pair<String, ImageVector>) -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.background,
        drawerContentColor = MaterialTheme.colorScheme.onBackground,
        windowInsets = WindowInsets(
            top = LocalSafeArea.current.calculateTopPadding() + 20.dp,
            bottom = LocalSafeArea.current.calculateBottomPadding()
        ),
    ) {
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Pokedex",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        Spacer(Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(
                icon = { Icon(item.second, contentDescription = null) },
                label = { Text(item.first) },
                selected = item == selectedItem,
                onClick = {
                    onItemsClick(item)
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary.copy(.6f),
                    unselectedContainerColor = Color.Transparent,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                ),
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}
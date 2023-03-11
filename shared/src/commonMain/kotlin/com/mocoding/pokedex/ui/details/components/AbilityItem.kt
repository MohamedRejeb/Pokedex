package com.mocoding.pokedex.ui.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun AbilityItem(
    name: String,
    containerColor: Color,
    modifier: Modifier = Modifier,
) {

    Text(
        text = name,
        color = Color.White,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(containerColor, CircleShape)
    )

}
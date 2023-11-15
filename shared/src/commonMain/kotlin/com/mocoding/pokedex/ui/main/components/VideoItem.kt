package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Video
import com.mocoding.pokedex.ui.theme.Black

@Composable
internal fun VideoItem(
    onClick: () -> Unit,
    video: Video,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .width(220.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                url = video.imageUrl,
                contentDescription = video.title,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Black.copy(.5f), BlendMode.Darken),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(MaterialTheme.shapes.medium)
            )

            Icon(
                Icons.Rounded.PlayArrow,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp),
            )

            Icon(
                Icons.Outlined.Circle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(60.dp),
            )
        }

        Text(
            text = video.title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall,

        )

        Text(
            text = "${video.year} | ${video.category} | ${video.details}",
            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
            style = MaterialTheme.typography.bodyMedium,
        )
    }

}
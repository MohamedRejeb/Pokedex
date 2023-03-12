package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.core.model.Video

@Composable
internal fun VideoRow(
    videoList: List<Video>,
    onVideoClicked: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyRow(
        contentPadding = PaddingValues(10.dp),
        modifier = modifier
    ) {
        items(videoList, key = { it.id }) { video ->
            VideoItem(
                onClick = { onVideoClicked(video.id) },
                video = video,
            )
        }
    }

}
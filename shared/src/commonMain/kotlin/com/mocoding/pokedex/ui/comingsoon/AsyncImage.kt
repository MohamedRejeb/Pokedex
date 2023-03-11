package com.mocoding.pokedex.ui.comingsoon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.ImageRequestState
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun AsyncImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(url)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = colorFilter,
            modifier = Modifier.fillMaxSize()
        )

        if (painter.requestState is ImageRequestState.Loading
            || painter.requestState is ImageRequestState.Failure) {
            Icon(
                Icons.Outlined.Image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(.4f)
                    .alpha(.6f)
            )
        }
    }
}
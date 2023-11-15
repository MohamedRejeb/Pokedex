package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox


@Composable
internal fun AsyncImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    modifier: Modifier = Modifier
) {
    Box(modifier, Alignment.Center) {
        val dataState by rememberUpdatedState(url)
        val request by remember {
            derivedStateOf {
                ImageRequest {
                    data(dataState)
                }
            }
        }

        AutoSizeBox(
            request,
            modifier.matchParentSize(),
        ) { action ->
            when (action) {
                is ImageAction.Loading -> {
                    CircularProgressIndicator()
                }
                is ImageAction.Success -> {
                    Image(
                        rememberImageSuccessPainter(action),
                        contentDescription = contentDescription,
                        contentScale = contentScale,
                        colorFilter = colorFilter,
                        modifier = Modifier.matchParentSize(),
                    )
                }
                is ImageAction.Failure -> {
                    Text(action.error.message ?: "Error")
                }
            }
        }
    }
}
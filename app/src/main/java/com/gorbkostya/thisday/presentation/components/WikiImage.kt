package com.gorbkostya.thisday.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest

@Composable
fun WikiImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val request = remember(context, imageUrl) {
        ImageRequest.Builder(context)
            .data(imageUrl)
            .httpHeaders(
                NetworkHeaders.Builder()
                    .set(
                        "User-Agent",
                        "ThisDay/1.0 (https://github.com/k1rmflyy/ThisDay)"
                    )
                    .build()
            )
            .build()
    }

    AsyncImage(
        model = request,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
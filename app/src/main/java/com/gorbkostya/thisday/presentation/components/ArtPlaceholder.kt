package com.gorbkostya.thisday.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val ArtColors = listOf(
    Color(0xFFFFE1A8),
    Color(0xFFF2A65A),
    Color(0xFFC9572C),
    Color(0xFF6E2A1A),
    Color(0xFF241713),
)

@Composable
fun ArtPlaceholder(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier.drawBehind {
            drawRect(
                brush = Brush.radialGradient(
                    colors = ArtColors,
                    center = Offset(size.width / 2f, size.height * 0.78f),
                    radius = size.maxDimension * 0.6f
                )
            )
        }
    ) {
        content()
    }
}
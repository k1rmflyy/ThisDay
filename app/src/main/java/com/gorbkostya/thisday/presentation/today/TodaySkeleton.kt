package com.gorbkostya.thisday.presentation.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.gorbkostya.thisday.presentation.components.SkeletonBox

@Composable
fun TodaySkeleton(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(176.dp),
                shape = RectangleShape
            )

            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SkeletonBox(
                    Modifier
                        .width(80.dp)
                        .height(26.dp)
                )
                SkeletonBox(
                    Modifier
                        .fillMaxWidth(0.6f)
                        .height(22.dp)
                )
                SkeletonBox(
                    Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                )
                SkeletonBox(
                    Modifier
                        .fillMaxWidth(0.85f)
                        .height(14.dp)
                )
            }
        }

        listOf(0.72f, 0.58f, 0.66f).forEach { titleWidth ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                SkeletonBox(
                    Modifier
                        .width(60.dp)
                        .height(22.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SkeletonBox(
                        Modifier
                            .fillMaxWidth(titleWidth)
                            .height(18.dp)
                    )
                    SkeletonBox(
                        Modifier
                            .fillMaxWidth()
                            .height(13.dp)
                    )
                }
            }
        }
    }
}
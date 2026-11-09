package com.gorbkostya.thisday.presentation.today

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gorbkostya.thisday.domain.HistoryEvent
import com.gorbkostya.thisday.presentation.components.ArtPlaceholder
import com.gorbkostya.thisday.presentation.theme.historyColors
import java.time.LocalDate

@Composable
fun HeroCard(
    event: HistoryEvent,
    today: LocalDate,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
    ) {

        ArtPlaceholder(
            modifier = Modifier
                .fillMaxWidth()
                .height(176.dp)
        ) {
            Text(
                text = "ГЛАВНОЕ",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Black.copy(alpha = 0.35f), RoundedCornerShape(13.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row {
                Text(
                    text = formatYear(event.year),
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 30.sp, lineHeight = 34.sp),
                    color = MaterialTheme.historyColors.gold,
                    modifier = Modifier
                        .alignByBaseline()
                )

                Spacer(Modifier.width(10.dp))

                Text(
                    text = yearsAgoText(event.year, today),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .alignByBaseline()
                )
            }

            Text(
                text = event.mainArticle?.title ?: formatYear(event.year),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = event.text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
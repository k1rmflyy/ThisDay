package com.gorbkostya.thisday.presentation.today

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gorbkostya.thisday.domain.HistoryEvent
import com.gorbkostya.thisday.presentation.theme.historyColors
import java.time.LocalDate


@Composable
fun TimelineItem(
    event: HistoryEvent,
    today: LocalDate,
    isLast: Boolean,
    modifier: Modifier = Modifier
) {

    val gold = MaterialTheme.historyColors.gold

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {

        Text(
            text = formatYear(event.year),
            style = MaterialTheme.typography.titleLarge,
            color = gold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .width(60.dp)
                .padding(top = 2.dp)
        )

        Spacer(Modifier.width(14.dp))

        Column(
            modifier = Modifier
                .width(12.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .padding(top = 7.dp)
                    .size(11.dp)
                    .background(MaterialTheme.colorScheme.onBackground, CircleShape)
                    .border(2.5.dp, gold, CircleShape)
            )

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .width(2.dp)
                        .weight(1f)
                        .background(
                            MaterialTheme.colorScheme.outlineVariant,
                            RoundedCornerShape(1.dp)
                        )
                )

            }
        }

        Spacer(Modifier.width(14.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 22.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = event.mainArticle?.title ?: formatYear(event.year),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = event.text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = yearsAgoText(event.year, today),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }
    }
}

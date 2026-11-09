package com.gorbkostya.thisday.presentation.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gorbkostya.thisday.R
import com.gorbkostya.thisday.presentation.components.RoundIconButton
import java.time.LocalDate

@Composable
fun TodayHeader(
    date: LocalDate,
    onCalendarClick: () -> Unit,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "ЭТОТ ДЕНЬ В ИСТОРИИ",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            RoundIconButton(
                icon = R.drawable.ic_calendar,
                contentDescription = "Выбрать дату",
                onClick = onCalendarClick
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = date.dayMonth(),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = date.weekdayAndYear(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            RoundIconButton(
                icon = R.drawable.ic_chevron_left,
                contentDescription = "Предыдущий день",
                onClick = onPreviousDay,
                size = 40.dp
            )

            Spacer(Modifier.width(8.dp))

            RoundIconButton(
                icon = R.drawable.ic_chevron_right,
                contentDescription = "Следующий день",
                onClick = onNextDay,
                size = 40.dp
            )
        }
    }
}
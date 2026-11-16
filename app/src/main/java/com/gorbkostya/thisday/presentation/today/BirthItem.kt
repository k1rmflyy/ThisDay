package com.gorbkostya.thisday.presentation.today

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gorbkostya.thisday.domain.Birth
import com.gorbkostya.thisday.presentation.theme.historyColors
import java.time.LocalDate

@Composable
fun BirthItem(
    birth: Birth,
    today: LocalDate,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = birth.article != null,
                onClick = onClick
            )
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = formatYear(birth.year),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.historyColors.gold
        )

        Text(
            text = birth.text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = yearsAgoText(birth.year, today),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
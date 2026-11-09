package com.gorbkostya.thisday.presentation.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gorbkostya.thisday.R
import com.gorbkostya.thisday.presentation.theme.historyColors

@Composable
fun TodayError(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, CircleShape)
                .size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_wifi_off),
                contentDescription = null,
                tint = MaterialTheme.historyColors.gold,
                modifier = Modifier
                    .size(48.dp)
            )
        }


        Text(
            text = "Нет подключения",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Не получилось загрузить события. Проверьте интернет и попробуйте ещё раз.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

    }

    Button(
        onClick = onRetry,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Повторить",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
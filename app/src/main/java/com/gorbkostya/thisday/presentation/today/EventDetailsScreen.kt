package com.gorbkostya.thisday.presentation.today

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gorbkostya.thisday.domain.HistoryEvent
import com.gorbkostya.thisday.presentation.components.ArtPlaceholder
import com.gorbkostya.thisday.presentation.components.WikiImage
import com.gorbkostya.thisday.presentation.theme.historyColors

@Composable
fun EventDetailsScreen(
    event: HistoryEvent,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    BackHandler(onBack = onBack)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(
            onClick = onBack
        ) {
            Text(
                text = "Назад",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        val imageUrl = event.mainArticle?.imageUrl
        if (!imageUrl.isNullOrBlank()) {
            ArtPlaceholder(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                WikiImage(
                    imageUrl = imageUrl,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }


        Text(
            text = formatYear(event.year),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.historyColors.gold
        )

        Text(
            text = "Событие",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = event.text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        TextButton(
            onClick = {
                val shareText = buildString {
                    appendLine(formatYear(event.year))
                    appendLine()
                    appendLine(event.text)
                    appendLine()
                    appendLine("Источник: Википедия")

                    val articleUrl = event.mainArticle?.imageUrl
                    if (articleUrl != null) {
                        appendLine(articleUrl)
                    }
                }

                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }

                context.startActivity(
                    Intent.createChooser(intent, null)
                )
            }
        ) {
            Text(
                text = "Поделиться",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }


        if (event.articles.isNotEmpty()) {
            Text(
                text = "Читайте в Википедии",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            event.articles.forEach { article ->
                TextButton(
                    onClick = {uriHandler.openUri(article.url)}
                ) {
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
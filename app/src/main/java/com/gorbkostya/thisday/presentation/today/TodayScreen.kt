package com.gorbkostya.thisday.presentation.today

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gorbkostya.thisday.presentation.theme.HistoryTheme
import java.time.LocalDate

@Composable
fun TodayScreen(
    viewModel: TodayViewModel = viewModel(factory = TodayViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val today = remember { LocalDate.now() }

    TodayScreenContent(
        state = state,
        today = today,
        onCommand = viewModel::onCommand
    )
}

@Composable
fun TodayScreenContent(
    state: TodayState,
    today: LocalDate,
    onCommand: (TodayCommand) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 32.dp)
        ) {
            item {
                TodayHeader(
                    date = state.date,
                    onCalendarClick = {},
                    onPreviousDay = {onCommand(TodayCommand.PreviousDay)},
                    onNextDay = {onCommand(TodayCommand.NextDay)}
                )
                Spacer(Modifier.height(24.dp))
            }

            when(val content = state.content) {
                is TodayContent.Content -> {
                    val highlight = content.day.highlight
                    val timeline = content.day.timeline()

                    if (highlight != null) {
                        item {
                            HeroCard(event = highlight, today = today, onClick = {})
                            Spacer(Modifier.height(28.dp))
                        }
                    }

                    item {
                        Text(
                            text = "ЕЩЕ В ЭТОТ ДЕНЬ",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
                        )
                    }

                    itemsIndexed(timeline) { index, event ->
                        TimelineItem(
                            event = event,
                            today = today,
                            isLast = index == timeline.lastIndex
                        )
                    }

                    item {
                        Text(
                            text = "Источник: Википедия · CC BY-SA",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 8.dp),
                        )
                    }
                }


                is TodayContent.Error -> item {
                    TodayError(onRetry = {onCommand(TodayCommand.Retry)})
                }
                TodayContent.Loading -> item {
                    TodaySkeleton()
                }
            }
        }
    }
}
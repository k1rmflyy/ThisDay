package com.gorbkostya.thisday.presentation.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayScreenContent(
    state: TodayState,
    today: LocalDate,
    onCommand: (TodayCommand) -> Unit,
    modifier: Modifier = Modifier
) {

    val uriHandler = LocalUriHandler.current
    val selectedEvent = state.selectedEvent
    val listState = rememberLazyListState()

    LaunchedEffect(state.date, state.tab) {
        listState.scrollToItem(0)
    }

    var showDatePicker by rememberSaveable {
        mutableStateOf(false)
    }

    if (selectedEvent != null) {
        EventDetailsScreen(
            event = selectedEvent,
            onBack = { onCommand(TodayCommand.CloseEvent) }
        )

        return
    }


    if (showDatePicker) {
        val pickerState = rememberDatePickerState(
            initialSelectedDateMillis = state.date
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli()
        )

        DatePickerDialog (
            onDismissRequest = {
                showDatePicker = false
            },

            confirmButton = {
                TextButton(
                    enabled = pickerState.selectedDateMillis != null,
                    onClick = {
                        val millis = pickerState.selectedDateMillis

                        if (millis != null) {
                            val date = Instant.ofEpochMilli(millis)
                                .atZone(ZoneOffset.UTC)
                                .toLocalDate()

                            onCommand(TodayCommand.PickDate(date))
                            showDatePicker = false
                        }
                    }
                ) {

                    Text(
                        text = "Выбрать",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },

            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text(
                        text = "Отмена",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
        ) {
            DatePicker(state = pickerState)
        }
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 32.dp)
        ) {
            item {
                TodayHeader(
                    date = state.date,
                    onCalendarClick = { showDatePicker = true },
                    onPreviousDay = { onCommand(TodayCommand.PreviousDay) },
                    onNextDay = { onCommand(TodayCommand.NextDay) }
                )
                Spacer(Modifier.height(24.dp))

                if (state.date != today) {
                    TextButton(
                        onClick = { onCommand(TodayCommand.GoToToday) }
                    ) {
                        Text(
                            text = "Сегодня",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }


            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TodayTab.entries.forEach { tab ->
                        val selected = state.tab == tab

                        val title = when(tab) {
                            TodayTab.EVENTS -> "События"
                            TodayTab.BIRTHS -> "Родились"
                            TodayTab.HOLIDAYS -> "Праздники"
                        }

                        TextButton(
                            onClick = {onCommand(TodayCommand.SelectTab(tab))},
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = if (selected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.Transparent
                                },

                                contentColor = if (selected) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            ),
                            contentPadding = PaddingValues(
                                horizontal = 4.dp,
                                vertical = 12.dp
                            )
                        ) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }



            when (val content = state.content) {
                is TodayContent.Content -> {
                    when (state.tab) {
                        TodayTab.EVENTS -> {
                            val highlight = content.day.highlight
                            val timeline = content.day.timeline()

                            if (highlight != null) {
                                item {
                                    HeroCard(
                                        event = highlight,
                                        today = today,
                                        onClick = { onCommand(TodayCommand.OpenEvent(highlight)) }
                                    )

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
                                    isLast = index == timeline.lastIndex,
                                    onClick = { onCommand(TodayCommand.OpenEvent(event)) }
                                )
                            }
                        }

                        TodayTab.BIRTHS -> {
                            val births = content.day.births

                            if (births.isEmpty()) {
                                item {
                                    Text(
                                        text = "На эту дату родившихся не найдено",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(vertical = 16.dp)
                                    )
                                }
                            } else {
                                itemsIndexed(births) { _, birth ->
                                   BirthItem(
                                       birth = birth,
                                       today = today,
                                       onClick = {
                                           val articleUrl = birth.article?.url

                                           if (articleUrl != null) {
                                               uriHandler.openUri(articleUrl)
                                           }
                                       }
                                   )
                                }
                            }
                        }


                        TodayTab.HOLIDAYS -> {
                            val holidays = content.day.holidays

                            if (holidays.isEmpty()) {
                                item {
                                    Text(
                                        text = "На эту дату праздников не найдено",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(vertical = 16.dp)
                                    )
                                }
                            } else {
                                itemsIndexed(holidays) { _, holiday ->
                                    HolidayItem(holiday = holiday)
                                }
                            }
                        }

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

                is TodayContent.Error -> {
                    item {
                        TodayError(onRetry = { onCommand(TodayCommand.Retry) })
                    }
                }

                TodayContent.Loading -> {
                    item {
                        TodaySkeleton()
                    }
                }
            }
        }
    }
}
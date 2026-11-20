package com.gorbkostya.thisday.presentation.today

import com.gorbkostya.thisday.domain.HistoryEvent
import java.time.LocalDate

sealed interface TodayCommand {
    data object Retry: TodayCommand
    data object PreviousDay: TodayCommand
    data object NextDay: TodayCommand
    data class PickDate(val date: LocalDate): TodayCommand
    data class SelectTab(val tab: TodayTab): TodayCommand

    data class OpenEvent(val event: HistoryEvent): TodayCommand

    data object CloseEvent: TodayCommand
}
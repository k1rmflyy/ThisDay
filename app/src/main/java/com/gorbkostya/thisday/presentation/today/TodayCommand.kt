package com.gorbkostya.thisday.presentation.today

import java.time.LocalDate

sealed interface TodayCommand {
    data object Retry: TodayCommand
    data object PreviousDay: TodayCommand
    data object NextDay: TodayCommand
    data class PickDate(val date: LocalDate): TodayCommand
    data class SelectTab(val tab: TodayTab): TodayCommand
}
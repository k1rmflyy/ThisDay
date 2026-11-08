package com.gorbkostya.thisday.presentation.today

import com.gorbkostya.thisday.domain.HistoryDay
import java.time.LocalDate

enum class TodayTab {
    EVENTS, BIRTHS, HOLIDAYS
}

sealed interface TodayContent {
    data object Loading : TodayContent
    data class Content(val day: HistoryDay) : TodayContent
    data class Error(val message: String?) : TodayContent
}


data class TodayState(
    val date: LocalDate,
    val tab: TodayTab = TodayTab.EVENTS,
    val content: TodayContent = TodayContent.Loading
)
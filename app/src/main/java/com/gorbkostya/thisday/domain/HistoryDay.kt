package com.gorbkostya.thisday.domain

data class HistoryDay(
    val month: Int,
    val day: Int,
    val selected: List<HistoryEvent>,
    val events: List<HistoryEvent>,
    val birth: List<Birth>,
    val holiday: List<Holiday>
)

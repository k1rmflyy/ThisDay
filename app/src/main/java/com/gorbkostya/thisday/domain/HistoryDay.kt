package com.gorbkostya.thisday.domain

data class HistoryDay(
    val month: Int,
    val day: Int,
    val selected: List<HistoryEvent>,
    val events: List<HistoryEvent>,
    val births: List<Birth>,
    val holidays: List<Holiday>
)

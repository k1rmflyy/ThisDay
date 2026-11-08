package com.gorbkostya.thisday

import com.gorbkostya.thisday.domain.HistoryDay
import com.gorbkostya.thisday.domain.HistoryEvent
import com.gorbkostya.thisday.domain.HistoryRepository

class FakeHistoryRepository : HistoryRepository {

    var result: Result<HistoryDay> = Result.success(testDay())
    val requestedDays = mutableListOf<Pair<Int, Int>>()

    override suspend fun getDay(month: Int, day: Int): Result<HistoryDay> {
        requestedDays += month to day
        return result
    }
}

fun testDay(month: Int = 10, day: Int = 30) = HistoryDay(
    month = month,
    day = day,
    selected = emptyList(),
    events = listOf(HistoryEvent(year = 1961, text = "Царь-бомба", articles = emptyList())),
    births = emptyList(),
    holidays = emptyList(),
)
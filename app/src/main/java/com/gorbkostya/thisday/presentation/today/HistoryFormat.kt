package com.gorbkostya.thisday.presentation.today

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import com.gorbkostya.thisday.R
import com.gorbkostya.thisday.domain.HistoryDay
import com.gorbkostya.thisday.domain.HistoryEvent
import com.gorbkostya.thisday.domain.yearsAgo
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val Russian = Locale.forLanguageTag("ru")
private val DayMonthFormatter = DateTimeFormatter.ofPattern("d MMMM", Russian)
private val WeekdayFormatter = DateTimeFormatter.ofPattern("EEEE", Russian)

fun LocalDate.dayMonth(): String = format(DayMonthFormatter)

fun LocalDate.weekdayAndYear(): String = "${format(WeekdayFormatter)} · $year"

fun formatYear(year: Int): String = if (year < 0) "${-year} до н. э." else year.toString()

@Composable
fun yearsAgoText(year: Int, today: LocalDate): String {
    val years = yearsAgo(year, today)
    return pluralStringResource(R.plurals.years_ago, years, years)
}


val HistoryDay.highlight: HistoryEvent?
    get() = selected.firstOrNull() ?: events.firstOrNull()

fun HistoryDay.timeline(): List<HistoryEvent> {
    val main = highlight
    return events
        .filter { it.text != main?.text }
        .sortedByDescending { it.year }
}
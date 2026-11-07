package com.gorbkostya.thisday.data.remote

import com.gorbkostya.thisday.data.remote.dto.OnThisDayItemDto
import com.gorbkostya.thisday.data.remote.dto.OnThisDayResponseDto
import com.gorbkostya.thisday.data.remote.dto.PageDTO
import com.gorbkostya.thisday.domain.Article
import com.gorbkostya.thisday.domain.Birth
import com.gorbkostya.thisday.domain.HistoryDay
import com.gorbkostya.thisday.domain.HistoryEvent
import com.gorbkostya.thisday.domain.Holiday

fun PageDTO.toDomain(): Article = Article(
    title = titles.normalized,
    description = description,
    extract = extract,
    imageUrl = thumbnail?.source,
    url = contentUrls.mobile.page
)

fun OnThisDayItemDto.toEvent(): HistoryEvent? {
    val eventYear = year ?: return null
    return HistoryEvent(
        year = eventYear,
        text = text,
        articles = pages.map { it.toDomain() }
    )
}

fun OnThisDayItemDto.toBirth(): Birth? {
    val birthYear = year ?: return null
    return Birth(
        year = birthYear,
        text = text,
        article = pages.firstOrNull()?.toDomain()
    )
}


fun OnThisDayItemDto.toHoliday(): Holiday = Holiday(
    text = text,
    articles = pages.map { it.toDomain() }
)


fun OnThisDayResponseDto.toDomain(month: Int, day: Int): HistoryDay = HistoryDay(
    month = month,
    day = day,
    selected = selected.mapNotNull { it.toEvent() },
    events = events.mapNotNull { it.toEvent() },
    births = births.mapNotNull { it.toBirth() },
    holidays = holidays.map { it.toHoliday() },
)
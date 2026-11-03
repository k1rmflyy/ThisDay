package com.gorbkostya.thisday.domain

import java.time.LocalDate

fun yearsAgo(year: Int, today: LocalDate): Int {
    val years = today.year - year
    return if (year < 0) years - 1 else years
}
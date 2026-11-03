package com.gorbkostya.thisday.domain

data class Birth(
    val year: Int,
    val text: String,
    val article: Article?
)

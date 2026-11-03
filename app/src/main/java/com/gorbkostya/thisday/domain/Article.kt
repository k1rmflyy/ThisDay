package com.gorbkostya.thisday.domain

data class Article(
    val title: String,
    val description: String?,
    val extract: String?,
    val imageUrl: String?,
    val url: String
)

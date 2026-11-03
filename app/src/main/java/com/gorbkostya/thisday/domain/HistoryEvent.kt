package com.gorbkostya.thisday.domain

data class HistoryEvent(
    val year: Int,
    val text: String,
    val articles: List<Article>
) {

    val mainArticle: Article?
        get() = articles.firstOrNull()
}

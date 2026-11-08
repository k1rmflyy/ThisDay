package com.gorbkostya.thisday.di

import com.gorbkostya.thisday.data.remote.WikipediaApi
import com.gorbkostya.thisday.data.remote.createHttpClient
import com.gorbkostya.thisday.data.repository.HistoryRepositoryImpl
import com.gorbkostya.thisday.domain.HistoryRepository

class AppContainer {
    private val httpClient = createHttpClient()
    private val api = WikipediaApi(httpClient)
    val historyRepository: HistoryRepository = HistoryRepositoryImpl(api)
}
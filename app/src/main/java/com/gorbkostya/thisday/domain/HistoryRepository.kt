package com.gorbkostya.thisday.domain

interface HistoryRepository {
    suspend fun getDay(month: Int, day: Int): Result<HistoryDay>
}
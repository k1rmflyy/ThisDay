package com.gorbkostya.thisday.data.repository

import androidx.compose.runtime.CancellationHandle
import com.gorbkostya.thisday.data.remote.WikipediaApi
import com.gorbkostya.thisday.data.remote.toDomain
import com.gorbkostya.thisday.domain.HistoryDay
import com.gorbkostya.thisday.domain.HistoryRepository
import io.ktor.utils.io.CancellationException

class HistoryRepositoryImpl(private val api: WikipediaApi) : HistoryRepository {
    override suspend fun getDay(
        month: Int,
        day: Int
    ): Result<HistoryDay> {
        return try {
            val dto = api.getOnThisDay(month, day)
            Result.success(dto.toDomain(month, day))
        } catch(e: CancellationException) {
            throw e
        } catch(e: Exception) {
            Result.failure(e)
        }
    }
}
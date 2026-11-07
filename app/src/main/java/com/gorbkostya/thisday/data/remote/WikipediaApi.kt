package com.gorbkostya.thisday.data.remote

import com.gorbkostya.thisday.data.remote.dto.OnThisDayResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WikipediaApi(private val client: HttpClient) {

    suspend fun getOnThisDay(month: Int, day: Int): OnThisDayResponseDto {
        val mm = month.toString().padStart(2, '0')
        val dd = day.toString().padStart(2, '0')
        return client.get("feed/onthisday/all/$mm/$dd").body()
    }
}
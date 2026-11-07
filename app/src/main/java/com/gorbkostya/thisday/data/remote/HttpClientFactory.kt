package com.gorbkostya.thisday.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


fun createHttpClient(): HttpClient = HttpClient(OkHttp) {
    expectSuccess = true

    install(ContentNegotiation) {
        json(Json {ignoreUnknownKeys = true})
    }

    install(Logging) {
        level = LogLevel.INFO
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("Ktor", message)
            }
        }
    }

    defaultRequest {
        url("https://ru.wikipedia.org/api/rest_v1/")
        headers.append(HttpHeaders.UserAgent, "https://github.com/k1rmflyy/ThisDay")
    }
}
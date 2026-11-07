package com.gorbkostya.thisday

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.gorbkostya.thisday.data.remote.WikipediaApi
import com.gorbkostya.thisday.data.remote.createHttpClient
import com.gorbkostya.thisday.data.remote.toDomain
import com.gorbkostya.thisday.presentation.theme.HistoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HistoryTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                )

                LaunchedEffect(Unit) {
                    val api = WikipediaApi(createHttpClient())
                    runCatching {
                        api.getOnThisDay(10, 30).toDomain(10, 30)
                    }

                        .onSuccess { day ->
                            Log.d("ThisDay", "events: ${day.events.size}, births: ${day.births.size}")
                            Log.d("ThisDay", "first: ${day.selected.firstOrNull()?.text}")

                        }
                        .onFailure { error ->
                            Log.e("ThisDay", "Не удалось загрузить", error)
                        }
                }
            }
        }
    }
}
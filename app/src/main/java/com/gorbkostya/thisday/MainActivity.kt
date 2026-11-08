package com.gorbkostya.thisday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gorbkostya.thisday.presentation.theme.HistoryTheme
import com.gorbkostya.thisday.presentation.today.TodayContent
import com.gorbkostya.thisday.presentation.today.TodayViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HistoryTheme {
                val viewModel: TodayViewModel = viewModel(factory = TodayViewModel.Factory)
                val state by viewModel.state.collectAsStateWithLifecycle()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    when(val content = state.content) {
                        is TodayContent.Content -> Text(
                            text = "${state.date}: событий ${content.day.events.size}",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        is TodayContent.Error -> Text(
                            text = "Ошибка: ${content.message}",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        TodayContent.Loading -> Text(
                            text = "Загрузка...",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                }

            }
        }
    }
}
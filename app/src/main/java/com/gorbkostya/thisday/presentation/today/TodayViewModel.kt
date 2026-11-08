package com.gorbkostya.thisday.presentation.today

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gorbkostya.thisday.ThisDayApp
import com.gorbkostya.thisday.domain.HistoryRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class TodayViewModel(
    private val repository: HistoryRepository,
    today: LocalDate = LocalDate.now()
): ViewModel() {

    private val _state = MutableStateFlow(TodayState(date = today))
    val state
        get() = _state.asStateFlow()

    private var loadJob: Job? = null

    init {
        load()
    }


    fun onCommand(command: TodayCommand) {
        when(command) {
            TodayCommand.NextDay -> changeDate(_state.value.date.plusDays(1))
            is TodayCommand.PickDate -> changeDate(command.date)
            TodayCommand.PreviousDay -> changeDate(_state.value.date.minusDays(1))
            TodayCommand.Retry -> load()
            is TodayCommand.SelectTab -> _state.update { it.copy(tab = command.tab) }
        }
    }

    private fun changeDate(date: LocalDate) {
        _state.update { it.copy(date = date) }
        load()
    }

    private fun load() {
        loadJob?.cancel()
        val date = _state.value.date
        _state.update { it.copy(content = TodayContent.Loading) }

        loadJob = viewModelScope.launch {
            repository.getDay(date.monthValue, date.dayOfMonth)
                .onSuccess { day ->
                    _state.update { it.copy(content = TodayContent.Content(day)) }
                }

                .onFailure { error ->
                    _state.update { it.copy(content = TodayContent.Error(error.message)) }
                }
        }
    }



    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ThisDayApp
                TodayViewModel(app.container.historyRepository)
            }
        }
    }
}
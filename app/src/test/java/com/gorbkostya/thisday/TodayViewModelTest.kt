package com.gorbkostya.thisday

import com.gorbkostya.thisday.presentation.today.TodayCommand
import com.gorbkostya.thisday.presentation.today.TodayContent
import com.gorbkostya.thisday.presentation.today.TodayTab
import com.gorbkostya.thisday.presentation.today.TodayViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

class TodayViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val today = LocalDate.of(2026, 10, 30)
    private val fake = FakeHistoryRepository()

    @Test
    fun `loads today on start`() {
        val viewModel = TodayViewModel(fake, today)

        assertTrue(viewModel.state.value.content is TodayContent.Content)
        assertEquals(listOf(10 to 30), fake.requestedDays)
    }

    @Test
    fun `shows error when request fails`() {
        fake.result = Result.failure(kotlinx.io.IOException("no network"))

        val viewModel = TodayViewModel(fake, today)

        assertTrue(viewModel.state.value.content is TodayContent.Error)
    }

    @Test
    fun `retry after error loads content`() {
        fake.result = Result.success(testDay())

        val viewModel = TodayViewModel(fake, today)
        viewModel.onCommand(TodayCommand.Retry)
        assertTrue(viewModel.state.value.content is TodayContent.Content)
    }

    @Test
    fun `next day changes date and loads it`() {
        val viewModel = TodayViewModel(fake, today)
        viewModel.onCommand(TodayCommand.NextDay)
        assertEquals(31, viewModel.state.value.date.dayOfMonth)
        assertEquals(10 to 31, fake.requestedDays.last())
    }

    @Test
    fun `select tab does not reload`() {
        val viewModel = TodayViewModel(fake, today)
        viewModel.onCommand(TodayCommand.SelectTab(TodayTab.BIRTHS))
        assertEquals(viewModel.state.value.tab, TodayTab.BIRTHS)
        assertEquals(1, fake.requestedDays.size)
    }
}
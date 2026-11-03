package com.gorbkostya.thisday

import com.gorbkostya.thisday.domain.yearsAgo
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class YearsAgoTest {

    private val today = LocalDate.of(2026, 10, 30)

    @Test
    fun `regular year`() {
        assertEquals(65, yearsAgo(1961, today))
    }

    @Test
    fun `same year is zero`() {
        assertEquals(0, yearsAgo(2026, today))
    }

    @Test
    fun `before common era skips year zero`() {
        assertEquals(2069, yearsAgo(-44, today))
    }

    @Test
    fun `first year bc`() {
        assertEquals(2026, yearsAgo(-1, today))
    }
}
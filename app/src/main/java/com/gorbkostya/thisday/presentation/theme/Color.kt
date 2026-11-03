package com.gorbkostya.thisday.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val LightBg = Color(0xFFF3EEE4)
val LightCard = Color(0xFFFFFCF6)
val LightMuted = Color(0xFFE9E2D4)
val LightText = Color(0xFF1E1A15)
val LightText2 = Color(0xFF6F665A)
val LightBorder = Color(0x141E1A15)
val LightAccent = Color(0xFF1F5F5B)
val LightOnAccent = Color(0xFFFFFFFF)
val LightAccentTint = Color(0x1A1F5F5B)
val LightGold = Color(0xFFB07A26)
val LightGoldTint = Color(0x21B07A26)




val DarkBg = Color(0xFF14120F)
val DarkCard = Color(0xFF1E1B16)
val DarkMuted = Color(0xFF2B271F)
val DarkText = Color(0xFFF2EBDD)
val DarkText2 = Color(0xFFA99F8E)
val DarkBorder = Color(0x14FFFFFF)
val DarkAccent = Color(0xFF7CC4B8)
val DarkOnAccent = Color(0xFF0F1F1D)
val DarkAccentTint = Color(0x267CC4B8)
val DarkGold = Color(0xFFE2B45E)
val DarkGoldTint = Color(0x26E2B45E)




@Immutable
data class HistoryColors(
    val gold: Color,
    val goldTint: Color,
    val accentTint: Color,
)

val LightHistoryColors = HistoryColors(
    gold = LightGold,
    goldTint = LightGoldTint,
    accentTint = LightAccentTint,
)

val DarkHistoryColors = HistoryColors(
    gold = DarkGold,
    goldTint = DarkGoldTint,
    accentTint = DarkAccentTint,
)
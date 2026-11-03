package com.gorbkostya.thisday.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = lightColorScheme(
    background = LightBg,
    onBackground = LightText,
    surface = LightCard,
    onSurface = LightText,
    surfaceVariant = LightMuted,
    onSurfaceVariant = LightText2,
    primary = LightAccent,
    onPrimary = LightOnAccent,
    outlineVariant = LightBorder,
)

private val DarkColorScheme = darkColorScheme(
    background = DarkBg,
    onBackground = DarkText,
    surface = DarkCard,
    onSurface = DarkText,
    surfaceVariant = DarkMuted,
    onSurfaceVariant = DarkText2,
    primary = DarkAccent,
    onPrimary = DarkOnAccent,
    outlineVariant = DarkBorder,
)

val LocalHistoryColors = staticCompositionLocalOf { LightHistoryColors }

@Composable
fun HistoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val historyColors = if (darkTheme) DarkHistoryColors else LightHistoryColors

    CompositionLocalProvider(LocalHistoryColors provides historyColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}

val MaterialTheme.historyColors: HistoryColors
    @Composable get() = LocalHistoryColors.current
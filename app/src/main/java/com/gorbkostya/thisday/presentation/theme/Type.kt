package com.gorbkostya.thisday.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.gorbkostya.thisday.R

val Playfair = FontFamily(
    Font(R.font.playfair_display_bold, FontWeight.Bold),
    Font(R.font.playfair_display_extrabold, FontWeight.ExtraBold),
)

val Golos = FontFamily(
    Font(R.font.golos_text_regular, FontWeight.Normal),
    Font(R.font.golos_text_medium, FontWeight.Medium),
    Font(R.font.golos_text_semibold, FontWeight.SemiBold),
)

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = Playfair, fontWeight = FontWeight.ExtraBold, fontSize = 64.sp, lineHeight = 64.sp, letterSpacing = (-0.02).em),
    displayMedium = TextStyle(fontFamily = Playfair, fontWeight = FontWeight.Bold, fontSize = 44.sp, lineHeight = 48.sp, letterSpacing = (-0.01).em),
    headlineMedium = TextStyle(fontFamily = Playfair, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 38.sp),
    headlineSmall = TextStyle(fontFamily = Playfair, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 30.sp),
    titleLarge = TextStyle(fontFamily = Playfair, fontWeight = FontWeight.Bold, fontSize = 20.sp, lineHeight = 24.sp),
    titleMedium = TextStyle(fontFamily = Golos, fontWeight = FontWeight.SemiBold, fontSize = 17.sp, lineHeight = 23.sp),
    titleSmall = TextStyle(fontFamily = Golos, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, lineHeight = 20.sp),
    bodyLarge = TextStyle(fontFamily = Golos, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 25.sp),
    bodyMedium = TextStyle(fontFamily = Golos, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall = TextStyle(fontFamily = Golos, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp),
    labelLarge = TextStyle(fontFamily = Golos, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 20.sp),
    labelMedium = TextStyle(fontFamily = Golos, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 18.sp),
    labelSmall = TextStyle(fontFamily = Golos, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 0.14.em),
)
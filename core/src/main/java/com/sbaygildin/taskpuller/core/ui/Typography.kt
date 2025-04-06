package com.sbaygildin.taskpuller.core.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sbaygildin.taskpuller.core.R

val SFUIText = FontFamily(
    Font(R.font.sf_ui_text_regular, FontWeight.Normal),
    Font(R.font.sf_ui_text_medium, FontWeight.Medium)
)

val AppTypography = androidx.compose.material3.Typography(
    bodyLarge = TextStyle(
        fontFamily = SFUIText,
        fontSize = 21.sp,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = TextStyle(
        fontFamily = SFUIText,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = SFUIText,
        fontSize = 31.sp,
        fontWeight = FontWeight.Bold
    )
)

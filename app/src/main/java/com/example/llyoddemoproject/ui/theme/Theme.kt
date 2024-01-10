package com.example.llyoddemoproject.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black


private val LightColorPalette = lightColors(
    primary = DarkGray,
    primaryVariant = Gray,
    secondary = LightPurple,
    onPrimary = Black,
    onSecondary = White,
)



@Composable
fun CoinTheme(content: @Composable() () -> Unit) {


    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
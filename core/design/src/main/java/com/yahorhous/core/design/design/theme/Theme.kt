package com.yahorhous.core.design.design.theme

import AppTypography
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Shapes
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable

@Composable
fun HHTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette.toMaterialColorScheme(darkTheme) else LightColorPalette.toMaterialColorScheme(darkTheme)

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
val AppShapes = Shapes()
@Immutable
data class AppColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val error: Color,
    val favoriteRed:Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onError: Color,
    val darkGreen:Color
)

private val LightColorPalette = AppColors(
    primary = Color(0xFF4CB24E),
    secondary = Color(0xFF00427D),
    background = Color(0xFF0C0C0C),
    surface = Color(0xFF222325),
    error = Color(0xFFB00020),
    favoriteRed = Color(0xFFFF0000),
    darkGreen = Color(0xFF015905),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val DarkColorPalette = AppColors(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    error = Color(0xFFCF6679),
    favoriteRed = Color(0xFFB00020),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
    darkGreen = Color(0xFF015905),
)

private fun AppColors.toMaterialColorScheme(darkTheme: Boolean): ColorScheme {
    return if (darkTheme) {
        darkColorScheme(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = surface,
            error = error,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface,
            onError = onError
        )
    } else {
        lightColorScheme(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = surface,
            error = error,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface,
            onError = onError
        )
    }
}

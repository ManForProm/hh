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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun HHTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors.toMaterialColorScheme(),
        typography = AppTypography,
        shapes = AppShapes
    ) {
        CompositionLocalProvider(
            LocalCustomColors provides colors,
            content = content
        )
    }
}
val AppShapes = Shapes()
@Immutable
data class AppColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val error: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onError: Color,
    val favoriteRed: Color,
    val darkGreen: Color,
    val customBlue: Color,
)

private val LightColorPalette = AppColors(
    primary = Color(0xFF4CB24E),
    secondary = Color(0xFF00427D),
    background = Color(0xFF0C0C0C),
    surface = Color(0xFF222325),
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
    favoriteRed = Color(0xFFFF0000),
    darkGreen = Color(0xFF015905),
    customBlue = Color(0xFF00427D),
)

private val DarkColorPalette = AppColors(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    error = Color(0xFFCF6679),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black,
    favoriteRed = Color(0xFFFF0000),
    darkGreen = Color(0xFF015905),
    customBlue = Color(0xFF00427D),
)

private fun AppColors.toMaterialColorScheme(): ColorScheme {
    return if (this == DarkColorPalette) {
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

internal val LocalCustomColors = staticCompositionLocalOf<AppColors> {
    error("Custom colors not provided")
}

val MaterialTheme.customColors: AppColors
    @Composable
    get() = LocalCustomColors.current

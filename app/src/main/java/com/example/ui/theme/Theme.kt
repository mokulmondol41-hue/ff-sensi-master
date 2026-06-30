package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = LightBlue,
    tertiary = SkyBlueAccent,
    background = BackgroundWhite,
    surface = CardWhite,
    onPrimary = PureWhite,
    onSecondary = PureWhite,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceVariantBlue,
    onSurfaceVariant = TextSecondaryBlue,
    outline = CardBorderBlue
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = false, // Force clean white & blue theme
    dynamicColor: Boolean = false, // Use our handcrafted white/blue theme colors
    content: @Composable () -> Unit,
) {
    MaterialTheme(colorScheme = AppColorScheme, typography = Typography, content = content)
}

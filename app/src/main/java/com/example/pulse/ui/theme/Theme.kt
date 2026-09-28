//package com.example.pulse.ui.theme
//
//import android.app.Activity
//import android.os.Build
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.darkColorScheme
//import androidx.compose.material3.dynamicDarkColorScheme
//import androidx.compose.material3.dynamicLightColorScheme
//import androidx.compose.material3.lightColorScheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.LocalContext
//
//import androidx.compose.ui.graphics.Color
//
////tambah sendiri
//// Definisikan warna tema terang (Light Theme) menggunakan warna kustom kita
//private val LightColorScheme = lightColorScheme(
//    primary = LimeGreen,
//    background = LightBackground,
//    surface = LightBackground,
//    onPrimary = DarkCard,
//    onBackground = DarkCard,
//    onSurface = DarkCard
//)
//
//// Definisikan warna tema gelap (Dark Theme) jika diperlukan nanti
//private val DarkColorScheme = darkColorScheme(
//    primary = LimeGreen,
//    background = DarkBackground,
//    surface = DarkCard,
//    onPrimary = Color.White,
//    onBackground = Color.White,
//    onSurface = Color.White
//)
//
////bawaan
//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)
//
////tambah sendiri
//@Composable
//fun PulseTheme(
//    darkTheme: Boolean = false, // Atur true jika ingin default gelap
//    content: @Composable () -> Unit
//) {
//    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography, // Memanggil Typography dari Type.kt yang sudah berfont Inter
//        content = content
//    )
//}
//
//// dari bawaan
//@Composable
//fun PulseTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}

package com.example.pulse.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Skema Warna Kustom Pulse
private val CustomDarkColorScheme = darkColorScheme(
    primary = LimeGreen,
    background = DarkBackground,
    surface = DarkCard,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val CustomLightColorScheme = lightColorScheme(
    primary = LimeGreen,
    background = LightBackground,
    surface = LightBackground,
    onPrimary = DarkCard,
    onBackground = DarkCard,
    onSurface = DarkCard
)

@Composable
fun PulseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Diset false agar warna kustom LimeGreen konsisten
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> CustomDarkColorScheme
        else -> CustomLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

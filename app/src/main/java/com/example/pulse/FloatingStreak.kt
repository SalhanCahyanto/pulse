package com.example.pulse

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LimeGreen

// Komponen utama untuk tombol streak mengambang (floating) yang bisa digeser
@Composable
fun DraggableStreakButton(
    streak: Int = 3,
    onClick: () -> Unit
) {

    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    // Ukuran floating button
    val buttonWidth = 86.dp
    val buttonHeight = 54.dp

    // Container pembungkus utama untuk mengetahui batas ukuran layar
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        val screenWidthPx = with(density) {
            maxWidth.toPx()
        }

        val screenHeightPx = with(density) {
            maxHeight.toPx()
        }

        val buttonWidthPx = with(density) {
            buttonWidth.toPx()
        }

        val buttonHeightPx = with(density) {
            buttonHeight.toPx()
        }

        val horizontalMarginPx = with(density) {
            12.dp.toPx()
        }

        val verticalMarginPx = with(density) {
            20.dp.toPx()
        }

        // Batas gerakan
        val minX = horizontalMarginPx

        val maxX =
            (screenWidthPx - buttonWidthPx - horizontalMarginPx)
                .coerceAtLeast(minX)

        val minY = verticalMarginPx

        // kasih jarak dari bottom navigation
        val bottomReservedSpace =
            with(density) {
                90.dp.toPx()
            }

        val maxY =
            (
                    screenHeightPx -
                            buttonHeightPx -
                            bottomReservedSpace
                    ).coerceAtLeast(minY)

        // State untuk menyimpan posisi horizontal (X) dari tombol
        var offsetX by remember {
            mutableFloatStateOf(0f)
        }

        // State untuk menyimpan posisi vertikal (Y) dari tombol
        var offsetY by remember {
            mutableFloatStateOf(0f)
        }

        // State untuk melacak apakah posisi awal sudah diatur
        var initialized by remember {
            mutableStateOf(false)
        }

        // Posisi awal:
        // kanan layar, agak ke tengah. Diatur saat pertama kali komponen dirender.
        LaunchedEffect(
            screenWidthPx,
            screenHeightPx
        ) {

            if (!initialized) {

                offsetX = maxX

                offsetY =
                    with(density) {
                        180.dp.toPx()
                    }
                        .coerceIn(
                            minY,
                            maxY
                        )

                initialized = true

            } else {

                // kalau orientation berubah,
                // pastikan button tetap di layar

                offsetX =
                    offsetX.coerceIn(
                        minX,
                        maxX
                    )

                offsetY =
                    offsetY.coerceIn(
                        minY,
                        maxY
                    )
            }
        }

        // UI Kotak/Tombol utama yang bisa di-drag
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = offsetX.roundToInt(),
                        y = offsetY.roundToInt()
                    )
                }

                // Shadow biar terasa floating
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(50)
                )

                .size(
                    width = buttonWidth,
                    height = buttonHeight
                )

                .background(
                    color = LimeGreen,
                    shape = RoundedCornerShape(50)
                )

                // Logika deteksi gestur pergerakan (drag) oleh pengguna
                .pointerInput(
                    screenWidthPx,
                    screenHeightPx
                ) {

                    detectDragGestures(

                        onDrag = { change, dragAmount ->

                            // supaya scroll homepage
                            // nggak ikut bergerak
                            change.consume()

                            offsetX =
                                (offsetX + dragAmount.x)
                                    .coerceIn(
                                        minX,
                                        maxX
                                    )

                            offsetY =
                                (offsetY + dragAmount.y)
                                    .coerceIn(
                                        minY,
                                        maxY
                                    )
                        },

                        onDragEnd = {

                            // ==============================
                            // SNAP KE SISI TERDEKAT
                            // Fungsi agar tombol otomatis menempel ke sisi kiri atau kanan layar saat dilepas
                            // ==============================

                            val middle =
                                screenWidthPx / 2

                            val targetX =
                                if (
                                    offsetX +
                                    buttonWidthPx / 2 <
                                    middle
                                ) {

                                    minX

                                } else {

                                    maxX
                                }

                            coroutineScope.launch {

                                animate(
                                    initialValue = offsetX,
                                    targetValue = targetX,
                                    animationSpec = tween(
                                        durationMillis = 180
                                    )
                                ) { value, _ ->

                                    offsetX = value
                                }
                            }
                        }
                    )
                }

                // Aksi saat tombol diklik (akan memicu navigasi ke halaman streak)
                .clickable {
                    onClick()
                },

            contentAlignment = Alignment.Center
        ) {

            // UI Bagian Konten Tombol (Ikon Api dan Angka Streak)
            Row(
                verticalAlignment =
                    Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = 14.dp
                )
            ) {

                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_fire
                    ),
                    contentDescription = "Streak",
                    tint = DarkCard,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    text = streak.toString(),
                    color = DarkCard,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

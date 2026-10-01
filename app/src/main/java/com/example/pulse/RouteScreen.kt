package com.example.pulse

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

// Layar Rute Peta (Peta GPS)
@Composable
fun GpsRouteScreen(navController: NavController) {
    // State untuk melacak posisi vertikal (geser ke atas/bawah) panel informasi
    var offsetY by remember { mutableFloatStateOf(0f) }
    
    // Batas geser panel: minOffset (geser maksimal ke atas) & maxOffset (geser maksimal ke bawah)
    val minOffset = -220f
    val maxOffset = 280f

    // Logika Animasi: Perubahan offset agar gerakan panel terasa mulus (spring physics) saat dilepas
    val animatedOffsetY by animateFloatAsState(
        targetValue = offsetY,
        animationSpec = spring(stiffness = 300f),
        label = "sheetOffsetAnimation"
    )

    // Pengaturan Peta (Google Maps)
    val startLocation = LatLng(37.4220, -122.0841)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startLocation, 15f)
    }
    
    // Konfigurasi antarmuka peta (menyembunyikan tombol zoom, menampilkan kompas)
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
                compassEnabled = true
            )
        )
    }

    // Container utama layar dengan sistem tumpukan
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. LATAR BELAKANG: PETA & RUTE GPS
        /* Bagian ini adalah kode GoogleMap asli yang saat ini dinonaktifkan sementara */
        /* GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings
        ) {
            // Placeholder for real GPS route
            // Polyline(
            //     points = listOf(startLocation, LatLng(37.4225, -122.0845)),
            //     color = LimeGreen,
            //     width = 10f
            // )
        } */
        
        // Placeholder layar kosong pengganti GoogleMap
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E222B)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "MAP VIEW ACTIVE\n(Google Maps Temporarily Disabled)",
                color = Color.White.copy(alpha = 0.3f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        // UI Tombol Back di pojok kiri atas
        IconButton(
            // Fungsi navigasi: Kembali ke halaman sebelumnya saat tombol panah kiri diklik
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 16.dp, start = 20.dp)
                .size(44.dp)
                .background(Color.Black.copy(alpha = 0.6f), CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = "Back",
                tint = Color.White
            )
        }

        // 2. PANEL BAWAH (ACTIVE SESSION) YANG BISA DIGESER (DRAG) KE ATAS & KE BAWAH
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                // Offset animasi untuk menggerakkan panel secara vertikal
                .offset(y = animatedOffsetY.dp)
                // Logika deteksi gestur geser (drag) oleh pengguna
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            // Fungsi Snap: Memosisikan panel ke batas terdekat saat jari dilepas
                            offsetY = when {
                                offsetY < -100f -> minOffset
                                offsetY > 120f -> maxOffset
                                else -> 0f
                            }
                        }
                    ) { _, dragAmount ->
                        // Logika batas geser: Mengatur batas agar panel tidak keluar dari layar ke atas maupun ke bawah
                        offsetY = (offsetY + dragAmount).coerceIn(minOffset, maxOffset)
                    }
                }
        ) {
            // Komponen Sheet (Panel Informasi)
            ActiveSessionSheet(
                onToggleMinimize = {
                    // Fungsi aksi: Buka / tutup panel secara cepat (toggle) ketika indikator geser atas/bawah diklik
                    offsetY = if (offsetY > 100f) 0f else maxOffset
                },
                onShareClick = {
                    // Fungsi navigasi: Kembali saat share diklik
                    navController.popBackStack()
                }
            )
        }
    }
}

// UI Komponen: Panel Bawah Informasi Sesi Aktif (Active Session Sheet)
@Composable
fun ActiveSessionSheet(onToggleMinimize: () -> Unit, onShareClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        colors = CardDefaults.cardColors(containerColor = LimeGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .navigationBarsPadding()
        ) {
            // UI Garis Penanda Geser (Handle Dragging) di bagian atas panel
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // Fungsi aksi: Memanggil toggle (buka/tutup) saat indikator ini diklik
                    .clickable { onToggleMinimize() }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.25f))
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // UI Bagian Header Panel (Status Sesi Aktif)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // Fungsi aksi: Memanggil toggle panel saat judul diklik
                    .clickable { onToggleMinimize() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "ACTIVE SESSION",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black.copy(alpha = 0.6f),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Morning Walking",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                }

                // UI Badge Indikator LIVE (Titik hijau dan teks Live)
                Surface(
                    shape = CircleShape,
                    color = Color.Black.copy(alpha = 0.08f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color(0xFF10B981), CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "LIVE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // UI Bagian 3 Kotak Statistik (Jarak, Kalori, Langkah)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Kartu Statistik Jarak Tempuh
                StatCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_map_pin,
                    label = "Distance",
                    value = "5.2 km"
                )
                // Kartu Statistik Kalori Terbakar
                StatCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_fire,
                    label = "Calories",
                    value = "320 kcal"
                )
                // Kartu Statistik Jumlah Langkah
                StatCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_footprints, // Or another icon if footprints doesn't exist
                    label = "Steps",
                    value = "8,432"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // UI Tombol Bagikan Rute (Share Route Button)
            Button(
                // Aksi saat tombol bagikan ditekan
                onClick = onShareClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkCard)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_route),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Share Route",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// UI Komponen Pembantu: Untuk menampilkan kotak data statistik kecil di panel bawah
@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    @androidx.annotation.DrawableRes iconRes: Int,
    label: String,
    value: String
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                color = TextGray,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
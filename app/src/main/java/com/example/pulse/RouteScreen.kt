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

@Composable
fun GpsRouteScreen(navController: NavController) {
    // State untuk melacak posisi vertikal panel
    var offsetY by remember { mutableFloatStateOf(0f) }
    
    // Batas geser: minOffset (geser ke atas) & maxOffset (geser ke bawah)
    val minOffset = -220f
    val maxOffset = 280f

    // Animasi perubahan offset agar geseran terasa mulus saat dilepas atau ditoggle
    val animatedOffsetY by animateFloatAsState(
        targetValue = offsetY,
        animationSpec = spring(stiffness = 300f),
        label = "sheetOffsetAnimation"
    )

    // Map Settings
    val startLocation = LatLng(37.4220, -122.0841)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startLocation, 15f)
    }
    
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
                compassEnabled = true
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. LATAR BELAKANG: PETA & RUTE GPS
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

        // Tombol Back to Community di pojok kiri atas
        IconButton(
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

        // 2. PANEL BAWAH (ACTIVE SESSION) YANG BISA DIDRAG KE ATAS & KE BAWAH
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = animatedOffsetY.dp)
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            // Snap ke posisi terdekat saat jari dilepas
                            offsetY = when {
                                offsetY < -100f -> minOffset
                                offsetY > 120f -> maxOffset
                                else -> 0f
                            }
                        }
                    ) { _, dragAmount ->
                        // Mengatur batas agar bisa digeser KE ATAS (negatif) maupun KE BAWAH (positif)
                        offsetY = (offsetY + dragAmount).coerceIn(minOffset, maxOffset)
                    }
                }
        ) {
            ActiveSessionSheet(
                onToggleMinimize = {
                    // Toggle buka / tutup cepat saat handle diklik
                    offsetY = if (offsetY > 100f) 0f else maxOffset
                },
                onShareClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

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
            // Garis Handle Dragging di bagian atas panel (dapat diklik)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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

            // Header Active Session & Status LIVE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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

                // Badge LIVE
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

            // 3 Kotak Statistik (Distance, Calories, Steps)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_map_pin,
                    label = "Distance",
                    value = "5.2 km"
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_fire,
                    label = "Calories",
                    value = "320 kcal"
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    iconRes = R.drawable.ic_footprints, // Or another icon if footprints doesn't exist
                    label = "Steps",
                    value = "8,432"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tombol Share Route
            Button(
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
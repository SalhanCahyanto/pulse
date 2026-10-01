package com.example.pulse

import androidx.navigation.NavController
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulse.ui.theme.*

@Composable
fun InsightScreen(navController: NavController) {

    // Container utama untuk layar Insight
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        // Lapisan Latar Belakang (Decorative background circle)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = 120.dp)
                .size(250.dp)
                .background(Color(0xFFD3D8DD).copy(alpha = 0.7f), shape = CircleShape)
        )

        // UI Lapisan Konten Utama yang bisa di-scroll secara vertikal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                // Padding bawah ekstra untuk memberi ruang pada navigasi melayang (floating nav)
                .padding(top = 48.dp, bottom = 120.dp), 
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InsightHeaderSection()
            Spacer(modifier = Modifier.height(8.dp))
            InsightCatatanHarianCard()
            InsightQuoteCard()
            InsightBioRhythmCard()
        }

        // Komponen Navigasi Bawah Melayang (Floating Bottom Nav) khusus layar Insight
        InsightFloatingBottomNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp, start = 24.dp, end = 24.dp),
            // Fungsi navigasi: Kembali ke halaman utama (Home) saat diklik
            onNavigateHome = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            },
            // Fungsi navigasi: Berpindah ke halaman profil saat diklik
            onNavigateProfile = {
                navController.navigate("profile") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            }
        )
    }
}

// UI Bagian Header (Menampilkan Judul Halaman Insight)
@Composable
fun InsightHeaderSection() {
    Column {
        Text(
            text = "Insight",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DarkCard
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Your wellness, synthesized",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGray
        )
    }
}

// Card Konten: Daftar Catatan Harian Pengguna
@Composable
fun InsightCatatanHarianCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LimeGreen, shape = RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_book_open),
                        contentDescription = "Book",
                        tint = DarkCard,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Catatan Harian",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DarkCard
                    )
                }
                Text(
                    text = "Today, 08:30 AM",
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkCard,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightBackground, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Felt strong during morning cardio today. |\nResting heart rate is down to 58 bpm.\nReady to scale up strength training...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkCard,
                    lineHeight = 22.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "24 words recorded",
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkCard
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Saved automatically",
                        style = MaterialTheme.typography.bodySmall,
                        color = DarkCard,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "Saved",
                        tint = DarkCard,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

// Card Konten: Menampilkan Kutipan Motivasi (Quote)
@Composable
fun InsightQuoteCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LimeGreen, shape = RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        Column {
            Text(
                text = "“The only bad workout is the one that didn't happen.”",
                style = MaterialTheme.typography.titleLarge,
                fontStyle = FontStyle.Italic,
                color = DarkCard,
                lineHeight = 28.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .height(2.dp)
                        .background(DarkCard)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "UNKNOWN AUTHOR",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkCard
                )
            }
        }
    }
}

// Card Konten: Grafik Bio-Rhythm Flow menggunakan Canvas
@Composable
fun InsightBioRhythmCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LimeGreen, shape = RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "Bio-Rhythm Flow",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkCard
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Abstract geometric synthesis of daily exertion",
                style = MaterialTheme.typography.bodySmall,
                color = DarkCard
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(DarkBackground, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Background geometric lines (subtle)
                    drawLine(
                        color = Color.LightGray.copy(alpha = 0.2f),
                        start = Offset(w * 0.25f, 0f),
                        end = Offset(w * 0.6f, h),
                        strokeWidth = 2f
                    )
                    
                    // Triangle outline on the right
                    val path = Path().apply {
                        moveTo(w * 0.7f, h * 0.8f)
                        lineTo(w * 0.85f, h * 0.2f)
                        lineTo(w * 0.95f, h * 0.9f)
                        close()
                    }
                    drawPath(
                        path = path,
                        color = Color.LightGray.copy(alpha = 0.2f),
                        style = Stroke(width = 2f, join = StrokeJoin.Round)
                    )

                    // Square outline on the left
                    drawRect(
                        color = LimeGreen,
                        topLeft = Offset(w * 0.08f, h * 0.5f),
                        size = Size(w * 0.15f, w * 0.15f),
                        style = Stroke(width = 2f)
                    )

                    // Main diagonal LimeGreen line
                    drawLine(
                        color = LimeGreen,
                        start = Offset(0f, h * 0.85f),
                        end = Offset(w, h * 0.1f),
                        strokeWidth = 4f,
                        cap = StrokeCap.Round
                    )
                    
                    // Small dot in the middle
                    drawCircle(
                        color = LimeGreen,
                        radius = 6f,
                        center = Offset(w * 0.48f, h * 0.49f) // Approximating intersection
                    )
                }
            }
        }
    }
}

// UI Komponen Pembantu: Kotak Bar Navigasi Bawah
@Composable
fun InsightFloatingBottomNav(
    modifier: Modifier = Modifier,
    onNavigateHome: () -> Unit,
    onNavigateProfile: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(LimeGreen, shape = CircleShape)
            .border(1.dp, DarkCard, shape = CircleShape),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        InsightNavItem(iconRes = R.drawable.ic_book_open, label = "Insight", isSelected = true, onClick = {})
        InsightNavItem(iconRes = R.drawable.ic_house, label = "Home", isSelected = false, onClick = onNavigateHome)
        InsightNavItem(iconRes = R.drawable.ic_user, label = "Profile", isSelected = false, onClick = onNavigateProfile)
    }
}

// UI Komponen Pembantu: Tombol Ikon Individual di Bar Navigasi
@Composable
fun InsightNavItem(iconRes: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        // Menangani aksi klik pada ikon navigasi
        modifier = Modifier.clickable { onClick() }
    ) {
        val backgroundColor = if (isSelected) LightBackground else Color.Transparent
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(backgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = DarkCard,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = DarkCard,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

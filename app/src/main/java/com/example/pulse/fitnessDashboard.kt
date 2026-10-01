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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulse.ui.theme.*

// Halaman Utama Dashboard Kebugaran
@Composable
fun FitnessDashboardScreen(navController: NavController) {
    // Container utama yang memungkinkan tumpukan elemen (overlay)
    Box(modifier = Modifier.fillMaxSize()) {
        // Scaffold memberikan struktur dasar layar termasuk Bottom Navigation
        Scaffold(
            bottomBar = {
                CustomBottomNavigation(
                    onNavigateInsight = {
                        navController.navigate("insight") {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    onNavigateProfile = {
                        navController.navigate("profile") {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            },
            containerColor = LightBackground
        ) { paddingValues ->
            // Konten utama dashboard yang bisa di-scroll
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                HeaderSection()
                Spacer(modifier = Modifier.height(32.dp))
                CircularProgressSection(navController = navController)
                Spacer(modifier = Modifier.height(24.dp))
                MetricsSection(navController = navController)
                Spacer(modifier = Modifier.height(24.dp))
                CommunitySection(navController = navController)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        
        // Komponen tombol streak melayang (floating) yang bisa digeser
        DraggableStreakButton(streak = 3) {
            // Fungsi navigasi untuk pindah ke layar milestone streak saat tombol diklik
            navController.navigate("streak_milestone")
        }
    }
}

// UI Bagian Header (Menampilkan tanggal, sapaan, dan ikon tema)
@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Tuesday, 15 September", color = TextGray, fontSize = 14.sp)
            Text(text = "Keep moving, Naip", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = DarkCard)
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_moon),
            contentDescription = "Dark Mode",
            modifier = Modifier.size(24.dp),
            tint = DarkCard
        )
    }
}

// UI Bagian Progress Lingkaran (Langkah Harian)
@Composable
fun CircularProgressSection(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center, 
            modifier = Modifier
                .size(220.dp)
                // Berpindah ke halaman detail langkah (step_count_detail) saat lingkaran diklik
                .clickable { navController.navigate("step_count_detail") }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Menggambar lintasan latar belakang (abu-abu)
                drawArc(
                    color = ProgressTrack,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Menggambar lintasan progres langkah saat ini (hijau)
                drawArc(
                    color = LimeGreen,
                    startAngle = -90f,
                    sweepAngle = 223f,
                    useCenter = false,
                    style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "6,245", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = DarkCard)
                Text(text = "daily steps", fontSize = 16.sp, color = TextGray)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.background(ProgressTrack, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(text = "62% OF 10,000", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DarkCard)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "3,755 steps to reach today's goal", color = TextGray, fontSize = 14.sp)
    }
}

// UI Bagian Metrik (Jarak dan Kalori)
@Composable
fun MetricsSection(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        // Card untuk menampilkan Jarak Tempuh (Distance)
        Card(
            modifier = Modifier
                .weight(1f)
                .height(140.dp)
                // Berpindah ke halaman detail jarak (distance_detail) saat diklik
                .clickable { navController.navigate("distance_detail") },
            colors = CardDefaults.cardColors(containerColor = LimeGreen),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(modifier = Modifier.size(40.dp).background(Color.Black.copy(alpha = 0.1f), RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                        Icon(painter = painterResource(id = R.drawable.ic_route), contentDescription = "Distance", tint = DarkCard, modifier = Modifier.size(20.dp))
                    }
                    Text("↗", fontWeight = FontWeight.Bold, color = DarkCard)
                }
                Column {
                    Text("4.8 km", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = DarkCard)
                    Text("Step Distance", fontSize = 14.sp, color = DarkCard.copy(alpha = 0.7f))
                }
            }
        }

        // Card untuk menampilkan Kalori Terbakar (Calories)
        Card(
            modifier = Modifier
                .weight(1f)
                .height(140.dp)
                // Berpindah ke halaman detail kalori (calories_detail) saat diklik
                .clickable { navController.navigate("calories_detail") },
            colors = CardDefaults.cardColors(containerColor = LimeGreen),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(modifier = Modifier.size(40.dp).background(Color.Black.copy(alpha = 0.1f), RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                        Icon(painter = painterResource(id = R.drawable.ic_fire), contentDescription = "Calories", tint = DarkCard, modifier = Modifier.size(20.dp))
                    }
                    Text("↗", fontWeight = FontWeight.Bold, color = DarkCard)
                }
                Column {
                    Text("450 kcal", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = DarkCard)
                    Text("Calories", fontSize = 14.sp, color = DarkCard.copy(alpha = 0.7f))
                }
            }
        }
    }
}

// UI Bagian Komunitas (Pembaruan Terbaru dari Teman)
@Composable
fun CommunitySection(navController: NavController) {
    Text(text = "Community", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkCard)
    Spacer(modifier = Modifier.height(12.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Berpindah ke halaman daftar komunitas utama (community) saat card diklik
            .clickable { navController.navigate("community") },
        colors = CardDefaults.cardColors(containerColor = LimeGreen),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).background(Color.Gray, CircleShape))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Sarah Jenkins", fontWeight = FontWeight.Bold, color = DarkCard)
                    Text("2h ago", fontSize = 12.sp, color = DarkCard.copy(alpha = 0.7f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.ic_map_pin), contentDescription = null, tint = LimeGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("8.4 km", color = Color.White, fontWeight = FontWeight.Bold)
                            Text("DISTANCE", color = TextGray, fontSize = 10.sp)
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.ic_clock), contentDescription = null, tint = LimeGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("5'12\" /km", color = Color.White, fontWeight = FontWeight.Bold)
                            Text("PACE", color = TextGray, fontSize = 10.sp)
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.ic_zap), contentDescription = null, tint = LimeGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("540 kcal", color = Color.White, fontWeight = FontWeight.Bold)
                            Text("CALORIES", color = TextGray, fontSize = 10.sp)
                        }
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 12.dp)) {
                Box(modifier = Modifier.background(DarkCard, RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.ic_fire), contentDescription = null, tint = TextGray, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("24", color = TextGray, fontSize = 12.sp)
                    }
                }
                Box(modifier = Modifier.background(DarkCard, RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.ic_massage), contentDescription = null, tint = TextGray, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("8", color = TextGray, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

// UI Bagian Navigasi Bawah (Bottom Navigation)
@Composable
fun CustomBottomNavigation(
    onNavigateInsight: () -> Unit,
    onNavigateProfile: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 32.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(LimeGreen, shape = CircleShape)
            .border(1.dp, DarkCard, shape = CircleShape),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DashboardNavItem(
            iconRes = R.drawable.ic_book_open,
            label = "Insight",
            isSelected = false,
            // Fungsi navigasi ke halaman Insight
            onClick = onNavigateInsight
        )
        DashboardNavItem(
            iconRes = R.drawable.ic_house,
            label = "Home",
            isSelected = true,
            onClick = {}
        )
        DashboardNavItem(
            iconRes = R.drawable.ic_user,
            label = "Profile",
            isSelected = false,
            // Fungsi navigasi ke halaman Profile
            onClick = onNavigateProfile
        )
    }
}

// Komponen pembantu untuk membuat satu tombol/item di navigasi bawah
@Composable
fun DashboardNavItem(iconRes: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        // Aksi yang dijalankan saat ikon navigasi diklik
        modifier = Modifier.clickable { onClick() }
    ) {
        val backgroundColor = if (isSelected) Color.White else Color.Transparent
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
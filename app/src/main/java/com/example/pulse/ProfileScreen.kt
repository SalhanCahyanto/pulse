package com.example.pulse

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.*

@Composable
fun ProfileScreen(navController: NavController) {
    // Container utama layar dengan background terang
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        // UI Lapisan Latar Belakang (Lingkaran abu-abu transparan di pojok kanan atas)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = 120.dp)
                .size(250.dp)
                .background(Color(0xFFD3D8DD).copy(alpha = 0.7f), shape = CircleShape)
        )

        // UI Lapisan Konten Utama yang bisa di-scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                // Padding ekstra di bawah agar konten tidak tertutup navigasi melayang (bottom nav)
                .padding(top = 48.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // UI Bagian Header (Judul Halaman Profile)
            Text(
                text = "My Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DarkCard,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            // Kartu Profil Pengguna. Bisa diklik untuk berpindah ke halaman edit profil.
            UserProfileCard(onClick = { navController.navigate("edit_profile") })
            
            // Kartu Detail Kesehatan. Bisa diklik untuk berpindah ke halaman edit detail kesehatan.
            HealthDetailsCard(onEditClick = { navController.navigate("edit_health") })
            
            // Kartu Target Bergerak (Move Goals). Bisa diklik untuk berpindah ke halaman edit move goals.
            MoveGoalsCard(onEditClick = { navController.navigate("edit_move_goal") })
        }

        // Komponen Navigasi Bawah Melayang (Floating Bottom Nav) khusus layar Profil
        ProfileBottomNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp, start = 24.dp, end = 24.dp),
            // Fungsi navigasi untuk kembali ke halaman Dashboard utama
            onNavigateHome = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            },
            // Fungsi navigasi untuk pindah ke halaman Insight
            onNavigateInsight = {
                navController.navigate("insight") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            }
        )
    }
}

// UI Komponen: Kartu Profil Pengguna (Menampilkan foto, nama, status aktif, dan level)
@Composable
fun UserProfileCard(onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(DarkCard)
            // Menangani aksi saat kartu ini diklik
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // UI Placeholder Gambar Profil Pengguna (Profile Image Placeholder)
            Box(modifier = Modifier.size(72.dp)) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .background(Color.Gray, shape = CircleShape)
                        .clip(CircleShape)
                )
                // UI Badge Verifikasi Level/Akun (Checkmark di pojok kanan bawah foto)
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.BottomEnd)
                        .background(LimeGreen, shape = CircleShape)
                        .border(2.dp, DarkCard, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "Verified",
                        tint = DarkCard,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Naip",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Active since March 2024",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                // UI Badge Level Pengguna (Misal: Level 12)
                Box(
                    modifier = Modifier
                        .background(DarkBackground, shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_zap),
                            contentDescription = "Level",
                            tint = LimeGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "LEVEL 12",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = LimeGreen
                        )
                    }
                }
            }
        }
    }
}

// UI Komponen: Kartu Detail Kesehatan (Menampilkan info biometrik dasar)
@Composable
fun HealthDetailsCard(onEditClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkCard, shape = RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            // UI Baris Header (Judul "Health Details" dan tombol Edit)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = "Health Details",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Health Details",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                
                // UI Tombol Edit (Kecil di pojok kanan)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF2D3C2C))
                        // Menangani aksi ketika tombol edit diklik
                        .clickable { onEditClick() }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pencil_line),
                            contentDescription = "Edit",
                            tint = LimeGreen,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Edit",
                            style = MaterialTheme.typography.labelMedium,
                            color = LimeGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // UI Grid Statistik (Berisi 4 data: Tanggal Lahir, Jenis Kelamin, Tinggi, Berat)
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    HealthDetailItem(
                        iconRes = R.drawable.ic_calendar,
                        label = "BIRTH DATE",
                        value = "18 May 1996",
                        modifier = Modifier.weight(1f)
                    )
                    HealthDetailItem(
                        iconRes = R.drawable.ic_venus,
                        label = "SEX",
                        value = "Male",
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    HealthDetailItem(
                        iconRes = R.drawable.ic_ruler,
                        label = "HEIGHT",
                        value = "175 cm",
                        modifier = Modifier.weight(1f)
                    )
                    HealthDetailItem(
                        iconRes = R.drawable.ic_weight,
                        label = "WEIGHT",
                        value = "62 kg",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

// UI Komponen Pembantu: Untuk menampilkan satu elemen statistik di dalam HealthDetailsCard
@Composable
fun HealthDetailItem(iconRes: Int, label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = TextGray,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = TextGray
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

// UI Komponen: Kartu Target Pergerakan Harian (Move Goals)
@Composable
fun MoveGoalsCard(onEditClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkCard, shape = RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            // UI Baris Header (Judul "Daily Move Goals" dan tombol edit)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fire),
                        contentDescription = "Move Goals",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Daily Move Goals",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                
                // UI Tombol Edit
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF2D3C2C))
                        // Menangani aksi saat tombol edit ini diklik
                        .clickable { onEditClick() }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pencil_line),
                            contentDescription = "Edit",
                            tint = LimeGreen,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Edit",
                            style = MaterialTheme.typography.labelMedium,
                            color = LimeGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // UI Baris Konten: Menampilkan jumlah target kalori dan Ikon Api
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "450",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "kilo kcal / Day",
                        style = MaterialTheme.typography.bodyMedium,
                        color = LimeGreen,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(LimeGreen, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fire),
                        contentDescription = "Fire",
                        tint = DarkCard,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

// UI Komponen Pembantu: Kotak Navigasi Bawah Mengambang (Floating Bottom Nav) khusus layar Profil
@Composable
fun ProfileBottomNav(
    modifier: Modifier = Modifier,
    onNavigateHome: () -> Unit,
    onNavigateInsight: () -> Unit
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
        ProfileNavItem(
            iconRes = R.drawable.ic_book_open, 
            label = "Insight", 
            isSelected = false, 
            onClick = onNavigateInsight
        )
        ProfileNavItem(
            iconRes = R.drawable.ic_house, 
            label = "Home", 
            isSelected = false, 
            onClick = onNavigateHome
        )
        ProfileNavItem(
            iconRes = R.drawable.ic_user, 
            label = "Profile", 
            isSelected = true, 
            onClick = {}
        )
    }
}

// UI Komponen Pembantu: Tombol Ikon Individual di Bar Navigasi
@Composable
fun ProfileNavItem(iconRes: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        // Logika fungsi klik saat ikon navigasi ditekan
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
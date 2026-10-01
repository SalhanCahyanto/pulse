package com.example.pulse

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.*

@Composable
fun EditMoveGoalsScreen(navController: NavController) {
    // State lokal untuk melacak nilai target kalori yang akan diubah
    var moveGoal by remember { mutableIntStateOf(450) }

    // Container utama layar
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp, bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // UI Bagian Header (Judul Halaman)
        Text(
            text = "Move Goals",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = DarkCard
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Teks Petunjuk Besar
        Text(
            text = "What is your daily target?",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            color = DarkCard,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Adjust your move goal to match your current baseline and lifestyle.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Card Interaktif Utama untuk mengatur Move Goal
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // UI Baris Counter (Tombol Minus, Angka, Tombol Plus)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Tombol Kurang (Minus)
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(DarkCard)
                            // Logika: Kurangi 50 kalori, batas minimal 50
                            .clickable { if (moveGoal > 50) moveGoal -= 50 },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = Color.White)
                    }

                    Text(
                        text = "$moveGoal",
                        style = MaterialTheme.typography.displayLarge.copy(fontSize = 64.sp),
                        fontWeight = FontWeight.Bold,
                        color = DarkCard,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )

                    // Tombol Tambah (Plus)
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(DarkCard)
                            // Logika: Tambah 50 kalori
                            .clickable { moveGoal += 50 },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "KILO KCAL / DAY",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                // UI Garis Penanda (Tick Marks) bergaya penggaris (hanya visual)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.height(24.dp)
                ) {
                    for (i in 0..10) {
                        val isCenter = i == 5
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(if (isCenter) 24.dp else if (i % 2 == 0) 16.dp else 8.dp)
                                .background(if (isCenter) LimeGreen else Color.LightGray)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // UI Baris Tombol Preset Cepat (Quick Actions)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Tombol Preset "Active baseline"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(50))
                            .border(1.dp, LimeGreen, RoundedCornerShape(50))
                            // Logika: Set move goal langsung ke 500
                            .clickable { moveGoal = 500 }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Active baseline",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = LimeGreen
                        )
                    }

                    // Tombol Preset "Moderate"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(50))
                            .background(DarkCard)
                            // Logika: Set move goal langsung ke 1000
                            .clickable { moveGoal = 1000 }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Moderate",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Info Card (Pesan Motivasi/Informasi Tambahan)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(AccentYellow.copy(alpha = 0.15f), shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chart_line),
                        contentDescription = null,
                        tint = AccentYellow,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Highly Achievable Goal",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = DarkCard
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Set your goals based on previous records to maintain a healthy active score.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // UI Bagian Footer (Informasi Target Sebelumnya)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Previous Target: 400",
                style = MaterialTheme.typography.labelMedium,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // UI Tombol Simpan (Save Button)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(LimeGreen)
                // Fungsi navigasi: Kembali ke halaman profil
                .clickable { navController.popBackStack() }
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Save Goal",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkCard
            )
        }
    }
}
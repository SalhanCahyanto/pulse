package com.example.pulse

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.shadow
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LightBackground
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview

// Halaman Pencapaian Streak (Beruntun)
@Composable
fun StreakMilestoneScreen(
    navController: NavController? = null,
    streak: Int = 3,
    // Default aksi tombol kembali (kembali ke halaman Home)
    onBackClick: () -> Unit = {
        navController?.navigate("home") {
            popUpTo("home") { inclusive = false }
            launchSingleTop = true
        }
    }
) {

    // Mendapatkan context untuk aksi Intent berbagi (Share)
    val context = LocalContext.current

    // Container utama layar
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // =======================
        // UI TOMBOL KEMBALI (BACK BUTTON)
        // =======================

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            // Tombol Back di pojok kiri atas
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFB8B8B8))
                    // Fungsi navigasi untuk kembali ke halaman sebelumnya
                    .clickable {
                        onBackClick()
                    },
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_left),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(19.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))


        // UI GRAFIK API & ANGKA STREAK (FIRE + STREAK NUMBER)


        Box(
            modifier = Modifier
                .size(width = 190.dp, height = 155.dp),
            contentAlignment = Alignment.Center
        ) {

            // Efek cahaya (Glow) di belakang ikon api
            Box(
                modifier = Modifier
                    .size(145.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                LimeGreen.copy(alpha = 0.45f),
                                LimeGreen.copy(alpha = 0.18f),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    )
            )

            // Ikon Api Utama
            Icon(
                painter = painterResource(id = R.drawable.ic_fire),
                contentDescription = "Streak Fire",
                tint = LimeGreen,
                modifier = Modifier.size(78.dp)
            )

            // Teks Angka Streak
            Text(
                text = streak.toString(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = DarkCard,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 15.dp, bottom = 5.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.25f),
                        ambientColor = Color.Black.copy(alpha = 0.25f)
                    )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        // UI JUDUL HALAMAN (TITLE)

        Text(
            text = "$streak-Day Streak!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DarkCard
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "You're on fire! Keep it going.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(34.dp))

        // =======================
        // UI KARTU PROGRES MINGGUAN (WEEKLY PROGRESS)
        // =======================

        WeeklyProgressCard(
            completedDays = streak
        )

        Spacer(modifier = Modifier.height(22.dp))


        // UI KARTU PENGHARGAAN (BADGE CARD)

        WarriorBadgeCard(
            currentDays = streak,
            targetDays = 7
        )

        Spacer(modifier = Modifier.weight(1f))


        // UI TOMBOL BAGIKAN (SHARE BUTTON)


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(LimeGreen)
                // Logika aksi saat tombol Share diklik
                .clickable {
                    // Membuat intent untuk membagikan teks pencapaian streak
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"

                        putExtra(
                            Intent.EXTRA_TEXT,
                            "🔥 I'm on a $streak-Day Streak! Keep moving!"
                        )
                    }

                    // Menjalankan aksi bagikan menggunakan menu dialog bawaan Android
                    context.startActivity(
                        Intent.createChooser(
                            shareIntent,
                            "Share Milestone"
                        )
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = DarkCard,
                modifier = Modifier.size(21.dp)
            )

            Spacer(modifier = Modifier.size(9.dp))

            Text(
                text = "Share Milestone",
                color = DarkCard,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


// KOMPONEN PEMBANTU: KARTU PROGRES MINGGUAN (WEEKLY PROGRESS)


@Composable
private fun WeeklyProgressCard(
    completedDays: Int
) {

    val days = listOf(
        "M",
        "T",
        "W",
        "T",
        "F",
        "S",
        "S"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFD2D6DC))
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )
    ) {

        Text(
            text = "WEEKLY PROGRESS",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Menampilkan indikator (kapsul) untuk setiap hari dalam seminggu
            days.forEachIndexed { index, day ->

                DayIndicator(
                    day = day,
                    completed = index < completedDays
                )
            }
        }
    }
}


// KOMPONEN PEMBANTU: INDIKATOR HARI (DAY INDICATOR)

@Composable
private fun DayIndicator(
    day: String,
    completed: Boolean
) {

    // Logika warna latar belakang: Hijau jika selesai, Biru/Hitam jika belum
    val backgroundColor =
        if (completed) LimeGreen
        else DarkCard

    // Logika warna konten (huruf dan titik): Biru/Hitam jika selesai, Abu-abu jika belum
    val contentColor =
        if (completed) DarkCard
        else Color(0xFF808790)

    Column(
        modifier = Modifier
            .size(
                width = 37.dp,
                height = 60.dp
            )
            .clip(RoundedCornerShape(19.dp))
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = day,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Titik bawah indikator
        if (completed) {
            // Titik hitam untuk hari yang telah diselesaikan
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(DarkCard)
            )
        } else {
            // Titik abu-abu untuk hari yang belum diselesaikan
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF555B63))
            )
        }
    }
}



// KOMPONEN PEMBANTU: 7 DAY WARRIOR BADGE


@Composable
private fun WarriorBadgeCard(
    currentDays: Int,
    targetDays: Int
) {

    // Logika perhitungan progres bar (0.0 - 1.0)
    val progress =
        currentDays.toFloat() / targetDays.toFloat()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFD2D6DC))
            .padding(18.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // UI Ikon Badge Penghargaan
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(
                        LimeGreen.copy(alpha = 0.38f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_award
                    ),
                    contentDescription = "Warrior Badge",
                    tint = LimeGreen,
                    modifier = Modifier.size(29.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Column {

                Text(
                    text = "7-Day Warrior Badge",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkCard
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$currentDays of $targetDays days completed",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGray
                )
            }
        }

        Spacer(modifier = Modifier.height(17.dp))

        // UI Bar Progres (Progress bar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(DarkCard)
        ) {

            // Indikator garis progres yang terisi
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(8.dp)
                    .background(LimeGreen)
            )
        }
    }
}

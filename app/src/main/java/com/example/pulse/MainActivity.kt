package com.example.pulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pulse.ui.theme.LightBackground
import com.example.pulse.ui.theme.PulseTheme

// Aktivitas utama (Main Activity) yang akan dijalankan pertama kali saat aplikasi dibuka
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Membungkus aplikasi dengan tema global (PulseTheme)
            PulseTheme {
                // Container utama aplikasi dengan warna latar belakang terang
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightBackground
                ) {
                    // Memanggil fungsi navigasi utama aplikasi
                    PulseAppNavigation()
                }
            }
        }
    }
}

// Konfigurasi Navigasi Utama Aplikasi
@Composable
fun PulseAppNavigation() {
    // State untuk menyimpan dan mengelola riwayat navigasi antar halaman
    val navController = rememberNavController()

    // Host navigasi (NavHost) yang menentukan halaman awal (startDestination)
    NavHost(navController = navController, startDestination = "welcome") {
        // Rute ke Halaman Layar Pembuka (Welcome Screen)
        composable("welcome") {
            FitnessWelcomeScreen(navController = navController)
        }
        // Rute ke Halaman Masuk (Sign In)
        composable("sign_in") {
            FitnessSignInScreen(navController = navController)
        }
        // Rute ke Halaman Data Personal (Onboarding)
        composable("personal_data") {
            PersonalDataScreen(navController = navController)
        }
        // Rute ke Halaman Utama (Dashboard)
        composable("home") {
            FitnessDashboardScreen(navController = navController)
        }
        // Rute ke Halaman Insight
        composable("insight") {
            InsightScreen(navController = navController)
        }
        // Rute ke Halaman Profil
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        // Rute ke Halaman Detail Jarak Tempuh
        composable("distance_detail") {
            DistanceDetailScreen(navController = navController)
        }
        // Rute ke Halaman Detail Langkah
        composable("step_count_detail") {
            StepCountDetailScreen(navController = navController)
        }
        // Rute ke Halaman Detail Kalori
        composable("calories_detail") {
            CaloriesDetailScreen(navController = navController)
        }
        // Rute ke Halaman Edit Data Kesehatan
        composable("edit_health") {
            EditHealthDetailsScreen(navController = navController)
        }
        // Rute ke Halaman Edit Target Kalori
        composable("edit_move_goal") {
            EditMoveGoalsScreen(navController = navController)
        }
        // Rute ke Halaman Edit Profil
        composable("edit_profile") {
            EditProfileScreen(navController = navController)
        }
        // Rute ke Halaman Komunitas
        composable("community") {
            CommunityFeedScreen(navController = navController)
        }
        // Rute ke Halaman Route Maps (Peta GPS)
        composable("route_screen") {
            GpsRouteScreen(navController = navController)
        }
        // Rute ke Halaman Milestone Streak
        composable("streak_milestone") {
            StreakMilestoneScreen(navController = navController)
        }
        // Rute ke Halaman Share Route Baru
        composable("share_route") {
            ShareRouteScreen(navController = navController)
        }
        // Rute ke Halaman Detail Postingan Komunitas
        composable("post_detail") {
            PostDetailScreen(navController = navController)
        }
    }
}
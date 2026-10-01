//package com.example.pulse
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.pulse.ui.theme.PulseTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            PulseTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PulseTheme {
//        Greeting("Android")
//    }
//}

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PulseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightBackground
                ) {
                    PulseAppNavigation()
                }
            }
        }
    }
}

// MainActivity.kt
@Composable
fun PulseAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            FitnessWelcomeScreen(navController = navController)
        }
        composable("sign_in") {
            FitnessSignInScreen(navController = navController)
        }
        composable("personal_data") {
            PersonalDataScreen(navController = navController)
        }
        composable("home") {
            FitnessDashboardScreen(navController = navController)
        }
        composable("insight") {
            InsightScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("distance_detail") {
            DistanceDetailScreen(navController = navController)
        }
        composable("step_count_detail") {
            StepCountDetailScreen(navController = navController)
        }
        composable("calories_detail") {
            CaloriesDetailScreen(navController = navController)
        }
        composable("edit_health") {
            EditHealthDetailsScreen(navController = navController)
        }
        composable("edit_move_goal") {
            EditMoveGoalsScreen(navController = navController)
        }
        composable("edit_profile") {
            EditProfileScreen(navController = navController)
        }
        composable("community") {
            CommunityFeedScreen(navController = navController)
        }
        composable("route_screen") {
            GpsRouteScreen(navController = navController)
        }
    }
}
package com.example.pulse

import androidx.navigation.NavController
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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

@Composable
fun FitnessDashboardScreen(navController: NavController) {
//    Scaffold(
//        bottomBar = {
//            CustomBottomNavigation(
//                onNavigateInsight = {
//                    navController.navigate("insight") {
//                        popUpTo("home")
//                        launchSingleTop = true
//                    }
//                }
//            )
//        },
//        containerColor = LightBackground
//    ) { paddingValues ->
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
            CircularProgressSection()
            Spacer(modifier = Modifier.height(24.dp))
            MetricsSection(navController = navController)
            Spacer(modifier = Modifier.height(24.dp))
            CommunitySection()
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

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

@Composable
fun CircularProgressSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(220.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawArc(
                    color = ProgressTrack,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
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

@Composable
fun MetricsSection(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        //stepdistance card
        Card(
            modifier = Modifier
                .weight(1f)
                .height(140.dp)
                // ADD THE CLICKABLE HERE:
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

        //caloricard
        Card(
            modifier = Modifier.weight(1f).height(140.dp),
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

@Composable
fun CommunitySection() {
    Text(text = "Community", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkCard)
    Spacer(modifier = Modifier.height(12.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
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

//sementara
//@Composable
//fun CustomBottomNavigation(onNavigateInsight: () -> Unit) {
//    Card(
//        modifier = Modifier.fillMaxWidth().padding(16.dp).height(70.dp),
//        colors = CardDefaults.cardColors(containerColor = LimeGreen),
//        shape = RoundedCornerShape(35.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.clickable { onNavigateInsight() }
//            ) {
//                Icon(painterResource(id = R.drawable.ic_book_open), contentDescription = "Insight", tint = DarkCard, modifier = Modifier.size(24.dp))
//                Spacer(modifier = Modifier.height(4.dp))
//                Text("Insight", color = DarkCard, fontSize = 12.sp, fontWeight = FontWeight.Medium)
//            }
//
//
////            Column(horizontalAlignment = Alignment.CenterHorizontally) {
////                Icon(painterResource(id = R.drawable.ic_book_open), contentDescription = "Insight", tint = DarkCard, modifier = Modifier.size(24.dp))
////                Spacer(modifier = Modifier.height(4.dp))
////                Text("Insight", color = DarkCard, fontSize = 12.sp, fontWeight = FontWeight.Medium)
////            }
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Box(modifier = Modifier.background(Color.White, CircleShape).padding(12.dp)) {
//                    Icon(painterResource(id = R.drawable.ic_house), contentDescription = "Home", tint = DarkCard, modifier = Modifier.size(24.dp))
//                }
//            }
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Icon(painterResource(id = R.drawable.ic_user), contentDescription = "Profile", tint = DarkCard, modifier = Modifier.size(24.dp))
//                Spacer(modifier = Modifier.height(4.dp))
//                Text("Profile", color = DarkCard, fontSize = 12.sp, fontWeight = FontWeight.Medium)
//            }
//        }
//    }
//}

// fitnessDashboard.kt
@Composable
fun CustomBottomNavigation(
    onNavigateInsight: () -> Unit,
    onNavigateProfile: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp).height(70.dp),
        colors = CardDefaults.cardColors(containerColor = LimeGreen),
        shape = RoundedCornerShape(35.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onNavigateInsight() }
            ) {
                Icon(painterResource(id = R.drawable.ic_book_open), contentDescription = "Insight", tint = DarkCard, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text("Insight", color = DarkCard, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.background(Color.White, CircleShape).padding(12.dp)) {
                    Icon(painterResource(id = R.drawable.ic_house), contentDescription = "Home", tint = DarkCard, modifier = Modifier.size(24.dp))
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onNavigateProfile() }
            ) {
                Icon(painterResource(id = R.drawable.ic_user), contentDescription = "Profile", tint = DarkCard, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text("Profile", color = DarkCard, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
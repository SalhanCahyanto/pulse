package com.example.pulse

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LightBackground
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray

@Composable
fun ShareRouteScreen(navController: NavController) {
    var caption by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = "Back",
                tint = DarkCard,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.popBackStack() }
                    .padding(4.dp)
            )

            Text(
                text = "New Post",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DarkCard
            )

            Text(
                text = "Share",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = LimeGreen,
                modifier = Modifier.clickable {
                    navController.navigate("community") {
                        popUpTo("community") { inclusive = true }
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // User Info Row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFFE5E7EB), CircleShape), // Light gray background
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "N",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = DarkCard
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Naip",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = DarkCard
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Interactive Text Input
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp)
            ) {
                if (caption.isEmpty()) {
                    Text(
                        text = "How was your workout today?\nAdd a caption...",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = TextGray.copy(alpha = 0.7f)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                BasicTextField(
                    value = caption,
                    onValueChange = { caption = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = DarkCard
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Route Preview Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // Makes it square
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF1E222B)) // Dark map background
            ) {
                // Simulated Map Background elements
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height
                    
                    // A dark green park shape
                    drawRoundRect(
                        color = Color(0xFF2A3628), // Dark forest green
                        topLeft = Offset(w * 0.5f, h * 0.1f),
                        size = androidx.compose.ui.geometry.Size(w * 0.45f, h * 0.5f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(40f, 40f)
                    )
                    
                    // Some city blocks
                    drawRoundRect(
                        color = Color.White.copy(alpha = 0.03f),
                        topLeft = Offset(w * 0.1f, h * 0.1f),
                        size = androidx.compose.ui.geometry.Size(w * 0.15f, h * 0.15f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
                    )
                    drawRoundRect(
                        color = Color.White.copy(alpha = 0.03f),
                        topLeft = Offset(w * 0.3f, h * 0.1f),
                        size = androidx.compose.ui.geometry.Size(w * 0.15f, h * 0.15f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
                    )
                    drawRoundRect(
                        color = Color.White.copy(alpha = 0.03f),
                        topLeft = Offset(w * 0.1f, h * 0.3f),
                        size = androidx.compose.ui.geometry.Size(w * 0.15f, h * 0.15f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
                    )
                     drawRoundRect(
                        color = Color.White.copy(alpha = 0.03f),
                        topLeft = Offset(w * 0.3f, h * 0.3f),
                        size = androidx.compose.ui.geometry.Size(w * 0.15f, h * 0.15f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
                    )

                    // Simulated Route Path
                    val path = Path().apply {
                        moveTo(w * 0.35f, h * 0.55f) // Start point
                        lineTo(w * 0.35f, h * 0.35f)
                        lineTo(w * 0.5f, h * 0.35f)
                        lineTo(w * 0.6f, h * 0.2f)
                        lineTo(w * 0.8f, h * 0.2f)
                        lineTo(w * 0.85f, h * 0.4f)
                        lineTo(w * 0.65f, h * 0.48f)
                        lineTo(w * 0.5f, h * 0.35f) // Loop back
                        lineTo(w * 0.45f, h * 0.35f) // Loop back
                    }

                    drawPath(
                        path = path,
                        color = LimeGreen,
                        style = Stroke(
                            width = 12f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                    
                    // Start Marker
                    drawCircle(
                        color = LimeGreen,
                        radius = 12f,
                        center = Offset(w * 0.35f, h * 0.55f)
                    )
                     // End Marker (hollow circle)
                    drawCircle(
                        color = LimeGreen,
                        radius = 12f,
                        center = Offset(w * 0.45f, h * 0.35f),
                        style = Stroke(width = 6f)
                    )
                }

                // Top Left Chip
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_footprints), // Substitute for shoe icon
                            contentDescription = null,
                            tint = LimeGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Morning walk",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                // Top Right Close Button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .size(32.dp)
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                     Text("X", color = LimeGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp) // Simple substitute for close icon
                }

                // Bottom Metrics Row
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(Color(0xFF242424), RoundedCornerShape(16.dp)) // Dark surface
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Distance
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_map_pin),
                            contentDescription = null,
                            tint = LimeGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "5.2 km",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "DISTANCE",
                                color = TextGray,
                                fontSize = 9.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }

                    // Calories
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_zap),
                            contentDescription = null,
                            tint = LimeGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "320 kcal",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "CALORIES",
                                color = TextGray,
                                fontSize = 9.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }

                    // Steps
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_footprints),
                            contentDescription = null,
                            tint = LimeGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "8,432",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "STEPS",
                                color = TextGray,
                                fontSize = 9.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
package com.example.pulse

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray

@Composable
fun FitnessWelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 1. Background Decorative Layer
        
        // Top Right Circle
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 100.dp, y = (-80).dp)
                .size(350.dp)
                .background(LimeGreen, shape = CircleShape)
        )
        
        // Middle Right Gray Shape
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = 100.dp, y = 100.dp)
                .width(250.dp)
                .height(100.dp)
                .rotate(-30f)
                .background(Color(0xFFF3F4F6), shape = RoundedCornerShape(50))
        )

        // Middle Left Lime Shape
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = (-80).dp, y = (-20).dp)
                .width(200.dp)
                .height(80.dp)
                .rotate(-30f)
                .background(LimeGreen, shape = RoundedCornerShape(50))
        )

        // Audio Line Icon
        Icon(
            painter = painterResource(id = R.drawable.ic_audio_lines),
            contentDescription = null,
            tint = Color(0xFFE5E7EB),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 24.dp, y = (-120).dp)
                .size(64.dp)
        )

        // 2. Foreground Content Layer
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(24.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SORA",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = DarkCard
                )
                
                Box(
                    modifier = Modifier
                        .background(LimeGreen, shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(Color.White, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "READY",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = DarkCard,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Center Content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Logo
//                Box(
//                    modifier = Modifier
//                        .size(120.dp)
//                        .shadow(elevation = 24.dp, shape = CircleShape, spotColor = DarkCard, ambientColor = DarkCard)
//                        .background(DarkCard, CircleShape),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_logo),
//                        contentDescription = "Logo",
//                        tint = LimeGreen,
//                        modifier = Modifier.size(48.dp)
//                    )
//                }
                //logo
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .shadow(elevation = 24.dp, shape = CircleShape, spotColor = DarkCard, ambientColor = DarkCard)
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.logo_sora),
                            contentDescription = "Logo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    }

                Spacer(modifier = Modifier.height(32.dp))

                // Title
                Text(
                    text = "Find Your\nRhythm",
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 48.sp,
                    color = DarkCard
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Subtitle
                Text(
                    text = "Track every workout, build momentum,\nand turn today's effort into progress.",
                    textAlign = TextAlign.Center,
                    color = TextGray,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Get Started Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(LimeGreen, shape = RoundedCornerShape(32.dp))
                        .clickable { navController.navigate("sign_in") }
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Get started",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = DarkCard
                    )
                    
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(DarkCard, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Forward",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login Link
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { navController.navigate("sign_in") }
                ) {
                    Text(
                        text = "Already training? ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextGray
                    )
                    Text(
                        text = "Sign in",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkCard
                    )
                }
            }
        }
    }
}
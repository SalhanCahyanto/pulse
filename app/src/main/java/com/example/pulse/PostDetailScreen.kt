package com.example.pulse

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray

@Composable
fun PostDetailScreen(navController: NavController) {
    var isLiked by remember { mutableStateOf(false) }
    var commentText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
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
                    text = "Post",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkCard
                )

                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = "Options",
                    tint = DarkCard,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .navigationBarsPadding()
            ) {
                HorizontalDivider(color = Color(0xFFF3F4F6))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .background(Color(0xFFF3F4F6), RoundedCornerShape(32.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (commentText.isEmpty()) {
                            Text(
                                text = "Write a comment...",
                                color = TextGray,
                                fontSize = 14.sp
                            )
                        }
                        BasicTextField(
                            value = commentText,
                            onValueChange = { commentText = it },
                            textStyle = TextStyle(color = DarkCard, fontSize = 14.sp),
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 4
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(LimeGreen, CircleShape)
                            .clip(CircleShape)
                            .clickable { commentText = "" },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowUpward,
                            contentDescription = "Send",
                            tint = DarkCard,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Post Content
            item {
                Spacer(modifier = Modifier.height(8.dp))
                // User Info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Gray, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        // Placeholder for image
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Sarah Jenkins", fontWeight = FontWeight.Bold, color = DarkCard, fontSize = 16.sp)
                        Text("2h ago", color = TextGray, fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Caption
                Text(
                    text = "Just crushed my morning run! New personal best on the river path today. Consistency is starting to pay off \uD83D\uDD25\uD83C\uDFC3",
                    color = DarkCard,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Route Card (Map Preview + Metrics)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(DarkCard)
                ) {
                    // Map Area
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xFF1E222B))
                    ) {
                         Canvas(modifier = Modifier.fillMaxSize()) {
                            val w = size.width
                            val h = size.height
                            
                            // Dark green park shape
                            drawRoundRect(
                                color = Color(0xFF2A3628),
                                topLeft = Offset(w * 0.5f, h * 0.1f),
                                size = Size(w * 0.45f, h * 0.8f),
                                cornerRadius = androidx.compose.ui.geometry.CornerRadius(40f, 40f)
                            )
                            
                            // City blocks
                            val blockColor = Color.White.copy(alpha = 0.03f)
                            drawRoundRect(blockColor, Offset(w * 0.1f, h * 0.2f), Size(w * 0.15f, h * 0.2f), androidx.compose.ui.geometry.CornerRadius(20f, 20f))
                            drawRoundRect(blockColor, Offset(w * 0.3f, h * 0.2f), Size(w * 0.15f, h * 0.2f), androidx.compose.ui.geometry.CornerRadius(20f, 20f))
                            drawRoundRect(blockColor, Offset(w * 0.1f, h * 0.5f), Size(w * 0.15f, h * 0.2f), androidx.compose.ui.geometry.CornerRadius(20f, 20f))
                            drawRoundRect(blockColor, Offset(w * 0.3f, h * 0.5f), Size(w * 0.15f, h * 0.2f), androidx.compose.ui.geometry.CornerRadius(20f, 20f))

                            // Route Path
                            val path = Path().apply {
                                moveTo(w * 0.25f, h * 0.8f) // Start point
                                lineTo(w * 0.25f, h * 0.45f)
                                lineTo(w * 0.5f, h * 0.45f)
                                lineTo(w * 0.6f, h * 0.25f)
                                lineTo(w * 0.8f, h * 0.3f)
                                lineTo(w * 0.75f, h * 0.6f)
                                lineTo(w * 0.6f, h * 0.7f)
                                lineTo(w * 0.45f, h * 0.45f) 
                            }

                            drawPath(
                                path = path,
                                color = LimeGreen,
                                style = Stroke(width = 8f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                            )
                            
                            // Markers
                            drawCircle(color = LimeGreen, radius = 10f, center = Offset(w * 0.25f, h * 0.8f))
                            drawCircle(color = LimeGreen, radius = 10f, center = Offset(w * 0.45f, h * 0.45f), style = Stroke(width = 6f))
                        }

                        // Top Left Chip
                        Box(
                            modifier = Modifier
                                .padding(12.dp)
                                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_footprints),
                                    contentDescription = null,
                                    tint = LimeGreen,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Morning run", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }

                    // Metrics Area
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Distance
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painterResource(id = R.drawable.ic_map_pin), contentDescription = null, tint = LimeGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text("8.4 km", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("DISTANCE", color = TextGray, fontSize = 9.sp, letterSpacing = 0.5.sp)
                            }
                        }
                        // Pace
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painterResource(id = R.drawable.ic_clock), contentDescription = null, tint = LimeGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text("5'12\"", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("PACE", color = TextGray, fontSize = 9.sp, letterSpacing = 0.5.sp)
                            }
                        }
                        // Calories
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painterResource(id = R.drawable.ic_zap), contentDescription = null, tint = LimeGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text("540 kcal", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("CALORIES", color = TextGray, fontSize = 9.sp, letterSpacing = 0.5.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Interactive Action Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Like Button
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(if (isLiked) LimeGreen else Color(0xFFF3F4F6))
                            .clickable { isLiked = !isLiked }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fire),
                            contentDescription = "Like",
                            tint = DarkCard,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Like", fontWeight = FontWeight.Medium, color = DarkCard, fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Comment Button
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFFF3F4F6))
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_massage), // Using massage as chat bubble
                            contentDescription = "Comment",
                            tint = DarkCard,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Comment", fontWeight = FontWeight.Medium, color = DarkCard, fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Likes/Comments Counts
                Row {
                    Text("24", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = DarkCard)
                    Text(" Likes  •  ", fontSize = 12.sp, color = TextGray)
                    Text("8", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = TextGray)
                    Text(" Comments", fontSize = 12.sp, color = TextGray)
                }

                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = Color(0xFFF3F4F6))
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Comments Section Header
            item {
                Text("Comments (8)", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkCard)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Comments List
            item {
                CommentItem("Marcus Vance", "1h", "That pace! You're on fire \uD83D\uDD25", "M")
            }
            item {
                CommentItem("Olivia Chen", "48m", "River path never disappoints. Nice work!", "O")
            }
            item {
                CommentItem("Alex Morgan", "32m", "Consistency wins. Keep crushing it \uD83D\uDCAA", "A")
            }
        }
    }
}

@Composable
fun CommentItem(name: String, time: String, text: String, initial: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFFF3F4F6), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(initial, fontWeight = FontWeight.Bold, color = DarkCard)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DarkCard)
                Spacer(modifier = Modifier.weight(1f))
                Text(time, fontSize = 12.sp, color = TextGray)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text, fontSize = 14.sp, color = DarkCard)
        }
    }
}
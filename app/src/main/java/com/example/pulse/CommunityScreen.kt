package com.example.pulse

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Tema Warna
val LimeGreen = Color(0xFFDDFB54)
val DarkSurface = Color(0xFF242424)
val LightBackground = Color(0xFFFBFBFB)
val TextGray = Color(0xFF8B8B8B)

@Composable
fun CommunityFeedScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = 32.dp, bottom = 120.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { TopHeader() }

            item {
                PostCard(
                    name = "Sarah Jenkins",
                    time = "2h ago",
                    content = "Just crushed my morning run! New personal best on the river path today. Consistency is starting to pay off 💪",
                    metrics = listOf(
                        MetricData(R.drawable.ic_map_pin, "8.4 km", "DISTANCE"),
                        MetricData(R.drawable.ic_clock, "5'12\" /km", "PACE"),
                        MetricData(R.drawable.ic_zap, "540 kcal", "CALORIES")
                    ),
                    likes = "24",
                    comments = "8"
                )
            }

            item {
                PostCard(
                    name = "Marcus Vance",
                    time = "5h ago",
                    content = "Evening yoga and mobility session complete. Feeling refreshed, aligned, and ready to attack the week ahead 🧘",
                    metrics = listOf(
                        MetricData(R.drawable.ic_clock, "45 min", "DURATION"),
                        MetricData(R.drawable.ic_heart_pulse, "112 bpm", "AVG HR"),
                        MetricData(R.drawable.ic_zap, "180 kcal", "CALORIES")
                    ),
                    likes = "24",
                    comments = "8"
                )
            }
        }

        // Overlay Navigation & FAB
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = LimeGreen,
                contentColor = Color.Black,
                shape = CircleShape,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Add Post"
                )
            }

            BottomNavigationMenu()
        }
    }
}

@Composable
fun TopHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Community",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                text = "ACTIVE NOW • 1,248 ONLINE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = TextGray,
                letterSpacing = 0.5.sp
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .background(LimeGreen, CircleShape)
                    .size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .background(LimeGreen, CircleShape)
                    .size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun PostCard(
    name: String,
    time: String,
    content: String,
    metrics: List<MetricData>,
    likes: String,
    comments: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = LimeGreen)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header: User Info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray) // Placeholder for Profile Picture
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    Text(text = time, fontSize = 12.sp, color = TextGray)
                }
                IconButton(onClick = { /* TODO */ }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pencil_line),
                        contentDescription = "More",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Body: Text Content
            Text(
                text = content,
                color = Color.Black,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Metrics Box
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(16.dp))
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                metrics.forEach { metric ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = metric.icon),
                            contentDescription = null,
                            tint = LimeGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = metric.value,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = metric.label,
                                color = TextGray,
                                fontSize = 9.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Footer: Actions
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ActionChip(iconRes = R.drawable.ic_fire, text = likes)
                ActionChip(iconRes = R.drawable.ic_massage, text = comments)
            }
        }
    }
}

@Composable
fun ActionChip(@DrawableRes iconRes: Int, text: String) {
    Row(
        modifier = Modifier
            .background(DarkSurface, CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = TextGray,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun BottomNavigationMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LimeGreen, RoundedCornerShape(32.dp))
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem(iconRes = R.drawable.ic_book_open, label = "Insight", isSelected = false)

        // Selected Home Item
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_house),
                    contentDescription = "Home",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Home", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = Color.Black)
        }

        NavItem(iconRes = R.drawable.ic_user, label = "Profile", isSelected = false)
    }
}

@Composable
fun NavItem(@DrawableRes iconRes: Int, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (isSelected) Color.Black else DarkSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color.Black else DarkSurface
        )
    }
}

data class MetricData(
    @param:DrawableRes val icon: Int,
    val value: String,
    val label: String
)

@Preview
@Composable
fun PreviewCommunityFeed() {
    MaterialTheme {
        CommunityFeedScreen()
    }
}

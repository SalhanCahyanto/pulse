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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "My Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DarkCard,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            UserProfileCard(onClick = { navController.navigate("edit_profile") })
            HealthDetailsCard(onEditClick = { navController.navigate("edit_health") })
            MoveGoalsCard(onEditClick = { navController.navigate("edit_move_goal") })
        }

        ProfileBottomNav(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp, start = 24.dp, end = 24.dp),
            onNavigateHome = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            },
            onNavigateInsight = {
                navController.navigate("insight") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            }
        )
    }
}

@Composable
fun UserProfileCard(onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(DarkCard)
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Profile Image Placeholder
            Box(modifier = Modifier.size(72.dp)) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .background(Color.Gray, shape = CircleShape)
                        .clip(CircleShape)
                )
                // Level Check Badge
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
                // Level Badge
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

@Composable
fun HealthDetailsCard(onEditClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkCard, shape = RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            // Header Row
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
                // Edit Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF2D3C2C))
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

            // Stats Grid
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

@Composable
fun MoveGoalsCard(onEditClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkCard, shape = RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            // Header Row
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
                // Edit Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF2D3C2C))
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

            // Content Row
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
            .border(1.dp, DarkCard, shape = CircleShape)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
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

@Composable
fun ProfileNavItem(iconRes: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
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
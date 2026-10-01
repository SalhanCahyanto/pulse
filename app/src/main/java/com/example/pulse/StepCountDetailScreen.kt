package com.example.pulse

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.*

@Composable
fun StepCountDetailScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Week") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Top Section (Dark Theme)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(DarkCard, shape = CircleShape)
                        .clip(CircleShape)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = "Step Count Details",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(end = 48.dp)
                )
                
                Spacer(modifier = Modifier.weight(1f))
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Hero Graphic
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            ) {
                // Outer Arc
                Canvas(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    drawArc(
                        color = Color.Gray.copy(alpha = 0.3f),
                        startAngle = 135f,
                        sweepAngle = 270f,
                        useCenter = false,
                        style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = LimeGreen,
                        startAngle = 135f,
                        sweepAngle = 200f, // Approx 7500 / 10000
                        useCenter = false,
                        style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                
                // Central Values inside the Arc
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_footprints),
                        contentDescription = "Steps",
                        tint = LimeGreen,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "7,500",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Steps Today",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextGray
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Daily Goal
            Text(
                text = "DAILY GOAL:",
                style = MaterialTheme.typography.labelMedium,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "10,000",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        
        // Bottom Section (Light Theme Sheet)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = LightBackground,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .padding(24.dp)
        ) {
            Column {
                // Tab Selector
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(24.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val tabs = listOf("Day", "Week", "Month", "Year")
                    tabs.forEach { tab ->
                        val isSelected = selectedTab == tab
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    color = if (isSelected) LimeGreen else Color.Transparent,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clip(RoundedCornerShape(20.dp))
                                .clickable { selectedTab = tab }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tab,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) DarkBackground else TextGray
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Dynamic Title Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${selectedTab}ly Overview",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DarkBackground
                    )
                    Text(
                        text = "Avg: 8,557 steps / day",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextGray
                    )
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Dynamic Scrollable Bar Chart
                StepCountChartSection(selectedTab = selectedTab)
                
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(20.dp))
                
                // Stats Footer
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StepStatItem(
                        iconRes = R.drawable.ic_arrow_up,
                        label = "DISTANCE",
                        value = "5,8 km",
                        subtitle = "Avg 6.2 km"
                    )
                    VerticalDivider(
                        modifier = Modifier.height(48.dp),
                        color = Color.LightGray
                    )
                    StepStatItem(
                        iconRes = R.drawable.ic_clock,
                        label = "ACTIVE TIME",
                        value = "48 mins",
                        subtitle = "Goal: 60 mins"
                    )
                    VerticalDivider(
                        modifier = Modifier.height(48.dp),
                        color = Color.LightGray
                    )
                    StepStatItem(
                        iconRes = R.drawable.ic_fire,
                        label = "CALORIES",
                        value = "320 kcal",
                        subtitle = "Burned today"
                    )
                }
            }
        }
    }
}

@Composable
fun StepStatItem(iconRes: Int, label: String, value: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                color = TextGray,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DarkBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.labelSmall,
            color = TextGray
        )
    }
}

data class StepChartData(val label: String, val value: Float, val isHighlighted: Boolean = false)

@Composable
fun StepCountChartSection(selectedTab: String, modifier: Modifier = Modifier) {
    val chartData = remember(selectedTab) {
        when (selectedTab) {
            "Day" -> (0..23).map {
                val label = "${it.toString().padStart(2, '0')}:00"
                val values = listOf(0.1f, 0.0f, 0.0f, 0.0f, 0.1f, 0.4f, 0.8f, 0.9f, 0.5f, 0.6f, 0.5f, 0.7f)
                val value = values[it % values.size]
                StepChartData(label, value, isHighlighted = it == 14)
            }
            "Month" -> listOf(
                StepChartData("Jan", 0.6f), StepChartData("Feb", 0.5f), StepChartData("Mar", 0.7f),
                StepChartData("Apr", 0.8f), StepChartData("May", 0.7f), StepChartData("Jun", 0.9f),
                StepChartData("Jul", 0.8f), StepChartData("Aug", 0.6f), StepChartData("Sep", 0.5f),
                StepChartData("Oct", 0.9f, true), StepChartData("Nov", 0.7f), StepChartData("Dec", 0.8f)
            )
            "Year" -> listOf(
                StepChartData("2021", 0.6f), StepChartData("2022", 0.7f), StepChartData("2023", 0.8f),
                StepChartData("2024", 0.9f), StepChartData("2025", 0.8f), StepChartData("2026", 1.0f, true)
            )
            "Week" -> listOf( 
                StepChartData("Mon", 0.6f), StepChartData("Tue", 0.4f), StepChartData("Wed", 0.9f, true),
                StepChartData("Thu", 0.3f), StepChartData("Fri", 0.7f), StepChartData("Sat", 1.0f), StepChartData("Sun", 0.5f)
            )
            else -> emptyList()
        }
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp), 
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        items(chartData) { data ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .fillMaxHeight(data.value * 0.75f)
                        .background(
                            color = if (data.isHighlighted) LimeGreen else LimeGreen.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
                        )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.label,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (data.isHighlighted) LimeGreen else TextGray,
                    fontWeight = if (data.isHighlighted) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}
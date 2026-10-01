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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.*

@Composable
fun DistanceDetailScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Week") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground) // Uses the DarkBackground for the top portion
    ) {
        // Top Section (Dark Theme)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes the available space above the bottom sheet
                .padding(horizontal = 24.dp, vertical = 48.dp),
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
                    text = "Distance Detail",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(end = 48.dp) // Offset for visual center
                )
                
                Spacer(modifier = Modifier.weight(1f))
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Hero Graphic
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(160.dp)
            ) {
                // Outer Arc
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawArc(
                        color = Color.Gray.copy(alpha = 0.3f),
                        startAngle = 135f,
                        sweepAngle = 270f,
                        useCenter = false,
                        style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = LimeGreen,
                        startAngle = 135f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                
                // Inner Center Circle
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(LimeGreen.copy(alpha = 0.2f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(LimeGreen, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_map_pin),
                            contentDescription = "Map Pin",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Values
            Text(
                text = "4.8 km",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "TOTAL DISTANCE",
                style = MaterialTheme.typography.labelMedium,
                color = TextGray
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
                
                Spacer(modifier = Modifier.height(24.dp))
                
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
                        text = "Avg: 1.5 km / day",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextGray
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Dynamic Scrollable Bar Chart
                DynamicChartSection(selectedTab = selectedTab)
                
                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(24.dp))
                
                // Stats Footer
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CardWhite, RoundedCornerShape(20.dp))
                        .padding(vertical = 16.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatItem(
                        iconRes = R.drawable.ic_chart_line, // Using chart line as average
                        label = "AVERAGE",
                        value = "3 Km"
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .height(32.dp)
                            .width(1.dp),
                        color = Color.LightGray
                    )
                    StatItem(
                        iconRes = R.drawable.ic_arrow_up,
                        label = "HIGHEST",
                        value = "6,5 Km"
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .height(32.dp)
                            .width(1.dp),
                        color = Color.LightGray
                    )
                    StatItem(
                        iconRes = R.drawable.ic_arrow_down_right,
                        label = "LOWEST",
                        value = "2,3 Km"
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun StatItem(iconRes: Int, label: String, value: String) {
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
    }
}

data class ChartData(val label: String, val value: Float, val isHighlighted: Boolean = false)

@Composable
fun DynamicChartSection(selectedTab: String, modifier: Modifier = Modifier) {
    // Generate deterministic dummy data based on the selected tab
    val chartData = remember(selectedTab) {
        when (selectedTab) {
            "Day" -> (0..23).map {
                val label = "${it.toString().padStart(2, '0')}:00"
                // Fixed pseudo-random looking values for consistency
                val values = listOf(0.3f, 0.2f, 0.4f, 0.3f, 0.5f, 0.6f, 0.8f, 0.9f, 0.7f, 0.6f, 0.5f, 0.4f)
                val value = values[it % values.size]
                ChartData(label, value, isHighlighted = it == 12)
            }
            "Month" -> listOf(
                ChartData("Jan", 0.4f), ChartData("Feb", 0.5f), ChartData("Mar", 0.6f),
                ChartData("Apr", 0.8f), ChartData("May", 0.7f), ChartData("Jun", 0.9f),
                ChartData("Jul", 1.0f, true), ChartData("Aug", 0.6f), ChartData("Sep", 0.5f),
                ChartData("Oct", 0.4f), ChartData("Nov", 0.5f), ChartData("Dec", 0.7f)
            )
            "Year" -> listOf(
                ChartData("2021", 0.5f), ChartData("2022", 0.7f), ChartData("2023", 0.6f),
                ChartData("2024", 0.9f), ChartData("2025", 0.8f), ChartData("2026", 1.0f, true)
            )
            "Week" -> listOf( // Default to Week
                ChartData("Mon", 0.6f), ChartData("Tue", 0.4f), ChartData("Wed", 0.8f, true),
                ChartData("Thu", 0.3f), ChartData("Fri", 0.7f), ChartData("Sat", 1.0f), ChartData("Sun", 0.5f)
            )
            else -> emptyList()
        }
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp), // Total height of the chart area
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
                        .fillMaxHeight(data.value * 0.75f) // Scale max height to 75% to leave room for text
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
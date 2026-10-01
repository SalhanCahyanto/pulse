package com.example.pulse

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.*

@Composable
fun CaloriesDetailScreen(navController: NavController) {
    // State untuk melacak tab yang aktif: "Day", "Week", "Month", "Year"
    var selectedTab by remember { mutableStateOf("Week") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Top Section (Pushes bottom sheet down)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
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
                    text = "Calories Details",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(end = 48.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.weight(0.5f))

            // Hero Graphic (Original Nested Boxes)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(LimeGreen.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .background(LimeGreen.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fire),
                            contentDescription = null,
                            tint = LimeGreen,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "450",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "/ 600 kcal",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextGray,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Calories Burned Today",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextGray
                )
            }
            
            Spacer(modifier = Modifier.weight(0.5f))
        }

        // Bottom Section (Light Sheet)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = LightBackground,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Tab Selector
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(24.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val tabs = listOf("Day", "Week", "Month", "Year")
                    tabs.forEach { tab ->
                        val isSelected = selectedTab == tab
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (isSelected) LimeGreen else Color.Transparent)
                                .clickable { selectedTab = tab }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tab,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) DarkBackground else TextGray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Dinamis Konten Grafik Berdasarkan Tab yang Dipilih
                when (selectedTab) {
                    "Day" -> DayOverviewContent()
                    "Week" -> WeeklyOverviewContent()
                    "Month" -> MonthlyOverviewContent()
                    "Year" -> YearlyOverviewContent()
                }

                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(24.dp))

                // Footer Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .padding(vertical = 16.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomMetricItem(
                        iconRes = R.drawable.ic_clock,
                        label = "BURNING TIME",
                        value = "04:20"
                    )
                    VerticalDivider(
                        modifier = Modifier.height(32.dp),
                        color = Color.LightGray
                    )
                    BottomMetricItem(
                        iconRes = R.drawable.ic_chart_line,
                        label = "ACTIVE KCAL",
                        value = "580 kcal"
                    )
                    VerticalDivider(
                        modifier = Modifier.height(32.dp),
                        color = Color.LightGray
                    )
                    BottomMetricItem(
                        iconRes = R.drawable.ic_fire,
                        label = "RESTING KCAL",
                        value = "290 kcal"
                    )
                }
            }
        }
    }
}

data class ChartItemData(
    val label: String,
    val value: Int
)

@Composable
fun ScrollableBarChartContent(
    title: String,
    subtitle: String,
    items: List<ChartItemData>,
    selectedLabel: String,
    initialScrollIndex: Int = 0,
    onItemSelected: (String) -> Unit = {}
) {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialScrollIndex)

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkBackground
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextGray,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Sequential Timeline Bar Chart (Scrollable forward into future & back into past)
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            itemsIndexed(items) { _, item ->
                val isSelected = item.label == selectedLabel

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(42.dp)
                        .clickable { onItemSelected(item.label) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        if (item.value > 0) {
                            Box(
                                modifier = Modifier
                                    .width(12.dp)
                                    .height(item.value.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isSelected) LimeGreen else LimeGreen.copy(alpha = 0.4f))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isSelected) DarkBackground else TextGray,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun DayOverviewContent() {
    var selectedHour by remember { mutableStateOf("18:00") }
    val hoursList = listOf("00:00", "02:00", "04:00", "06:00", "08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00")

    val dayItems = remember {
        val currentHourIndex = 33 
        (0 until 60).map { idx ->
            val hourLabel = hoursList[idx % hoursList.size]
            val value = if (idx <= currentHourIndex) {
                when (idx % 6) {
                    0 -> 20; 1 -> 15; 2 -> 35; 3 -> 85; 4 -> 60; else -> 100
                }
            } else 0
            ChartItemData(hourLabel, value)
        }
    }

    ScrollableBarChartContent(
        title = "Today's Hourly Burn",
        subtitle = "Avg: 450 kcal / day",
        items = dayItems,
        selectedLabel = selectedHour,
        initialScrollIndex = 28,
        onItemSelected = { selectedHour = it }
    )
}

@Composable
fun WeeklyOverviewContent() {
    var selectedDay by remember { mutableStateOf("Wed") }
    val daysList = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val weekItems = remember {
        val currentDayIndex = 30 
        (0 until 70).map { idx ->
            val dayLabel = daysList[idx % daysList.size]
            val value = if (idx <= currentDayIndex) {
                when (idx % 7) {
                    0 -> 70; 1 -> 50; 2 -> 90; 3 -> 45; 4 -> 100; 5 -> 120; else -> 65
                }
            } else 0
            ChartItemData(dayLabel, value)
        }
    }

    ScrollableBarChartContent(
        title = "Weekly Overview",
        subtitle = "Avg: 410 kcal / day",
        items = weekItems,
        selectedLabel = selectedDay,
        initialScrollIndex = 26,
        onItemSelected = { selectedDay = it }
    )
}

@Composable
fun MonthlyOverviewContent() {
    var selectedDay by remember { mutableStateOf("15") }

    val monthItems = remember {
        val currentDayIndex = 44 
        (0 until 90).map { idx ->
            val dayNum = (idx % 30) + 1
            val value = if (idx <= currentDayIndex) {
                when (dayNum % 7) {
                    1 -> 65; 2 -> 80; 3 -> 110; 4 -> 50; 5 -> 95; 6 -> 120; else -> 75
                }
            } else 0
            ChartItemData("$dayNum", value)
        }
    }

    ScrollableBarChartContent(
        title = "Monthly Progress",
        subtitle = "Avg: 12,400 kcal / mo",
        items = monthItems,
        selectedLabel = selectedDay,
        initialScrollIndex = 38,
        onItemSelected = { selectedDay = it }
    )
}

@Composable
fun YearlyOverviewContent() {
    var selectedMonth by remember { mutableStateOf("Jul") }
    val monthsList = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    val yearItems = remember {
        val currentMonthIndex = 18 
        (0 until 36).map { idx ->
            val monthLabel = monthsList[idx % monthsList.size]
            val value = if (idx <= currentMonthIndex) {
                when (idx % 12) {
                    0 -> 60; 1 -> 75; 2 -> 90; 3 -> 85; 4 -> 110; 5 -> 105; 6 -> 120; 7 -> 115; 8 -> 95; else -> 70
                }
            } else 0
            ChartItemData(monthLabel, value)
        }
    }

    ScrollableBarChartContent(
        title = "Yearly Statistics",
        subtitle = "Avg: 11,800 kcal / mo",
        items = yearItems,
        selectedLabel = selectedMonth,
        initialScrollIndex = 14,
        onItemSelected = { selectedMonth = it }
    )
}

@Composable
fun BottomMetricItem(
    @DrawableRes iconRes: Int,
    label: String,
    value: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = TextGray,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = DarkBackground,
            fontWeight = FontWeight.Bold
        )
    }
}

package com.example.pulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulse.ui.theme.PulseTheme

// Palet Warna Sesuai Desain Calories Details
val CaloriesBg = Color(0xFF1E222B)
val NeonLime = Color(0xFFDDFB54)
val SheetSurface = Color(0xFFF3F4F6)
val CardWhite = Color(0xFFFFFFFF)
val TextDark = Color(0xFF111827)
val TextMuted = Color(0xFF6B7280)

class CaloriesDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PulseTheme {
                CaloriesDetailScreen(onBackClick = { finish() })
            }
        }
    }
}

@Composable
fun CaloriesDetailScreen(onBackClick: () -> Unit = {}) {
    // State untuk melacak tab yang aktif: "Day", "Week", "Month", "Year"
    var selectedTab by remember { mutableStateOf("Week") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CaloriesBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 16.dp)
        ) {
            // Header: Tombol Back & Judul
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White.copy(alpha = 0.15f), CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Calories Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Progress Lingkar Grafik & Angka Kalori
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Simulasi Ring Progress / Icon Tengah
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(NeonLime.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .background(NeonLime.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fire),
                            contentDescription = null,
                            tint = NeonLime,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "450",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "/ 600 kcal",
                        fontSize = 16.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Calories Burned Today",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Sheet Putih Konten Detail Grafik
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = SheetSurface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .navigationBarsPadding()
                ) {
                    // Tab Selector (Day, Week, Month, Year)
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
                                    .background(if (isSelected) NeonLime else Color.Transparent)
                                    .clickable { selectedTab = tab }
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) TextDark else TextMuted
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

                    Spacer(modifier = Modifier.weight(1f))

                    // Bagian Bawah: Burning Time, Active Kcal, Resting Kcal
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CardWhite, RoundedCornerShape(20.dp))
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
                            modifier = Modifier
                                .height(32.dp)
                                .width(1.dp),
                            color = Color.LightGray
                        )
                        BottomMetricItem(
                            iconRes = R.drawable.ic_chart_line,
                            label = "ACTIVE KCAL",
                            value = "580 kcal"
                        )
                        VerticalDivider(
                            modifier = Modifier
                                .height(32.dp)
                                .width(1.dp),
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
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextMuted,
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
                                    .background(if (isSelected) NeonLime else NeonLime.copy(alpha = 0.4f))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        color = if (isSelected) TextDark else TextMuted,
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

    // Timeline: 60 2-hour slots (past slots have values, future slots have 0 value = no bar)
    val dayItems = remember {
        val currentHourIndex = 33 // 18:00
        (0 until 60).map { idx ->
            val hourLabel = hoursList[idx % hoursList.size]
            val value = if (idx <= currentHourIndex) {
                when (idx % 6) {
                    0 -> 20; 1 -> 15; 2 -> 35; 3 -> 85; 4 -> 60; else -> 100
                }
            } else {
                0 // Future hours
            }
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

    // Timeline: 70 days (10 weeks) (past days have values, future days have 0 value = no bar)
    val weekItems = remember {
        val currentDayIndex = 30 // Wed of current week
        (0 until 70).map { idx ->
            val dayLabel = daysList[idx % daysList.size]
            val value = if (idx <= currentDayIndex) {
                when (idx % 7) {
                    0 -> 70; 1 -> 50; 2 -> 90; 3 -> 45; 4 -> 100; 5 -> 120; else -> 65
                }
            } else {
                0 // Future days
            }
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

    // Timeline: 90 days (3 months) (past days have values, future days have 0 value = no bar)
    val monthItems = remember {
        val currentDayIndex = 44 // Day 15 of current month
        (0 until 90).map { idx ->
            val dayNum = (idx % 30) + 1
            val value = if (idx <= currentDayIndex) {
                when (dayNum % 7) {
                    1 -> 65; 2 -> 80; 3 -> 110; 4 -> 50; 5 -> 95; 6 -> 120; else -> 75
                }
            } else {
                0 // Future days
            }
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

    // Timeline: 36 months (3 years) (past months have values, future months have 0 value = no bar)
    val yearItems = remember {
        val currentMonthIndex = 18 // Jul of current year
        (0 until 36).map { idx ->
            val monthLabel = monthsList[idx % monthsList.size]
            val value = if (idx <= currentMonthIndex) {
                when (idx % 12) {
                    0 -> 60; 1 -> 75; 2 -> 90; 3 -> 85; 4 -> 110; 5 -> 105; 6 -> 120; 7 -> 115; 8 -> 95; else -> 70
                }
            } else {
                0 // Future months
            }
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
                tint = TextMuted,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                fontSize = 9.sp,
                color = TextMuted,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = TextDark,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCaloriesDetail() {
    PulseTheme {
        CaloriesDetailScreen()
    }
}
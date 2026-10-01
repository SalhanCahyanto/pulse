package com.example.pulse

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.shadow
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LightBackground
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StreakMilestoneScreen(
    navController: NavController? = null,
    streak: Int = 3,
    onBackClick: () -> Unit = {
        navController?.navigate("home") {
            popUpTo("home") { inclusive = false }
            launchSingleTop = true
        }
    }
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // =======================
        // BACK BUTTON
        // =======================

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFB8B8B8))
                    .clickable {
                        onBackClick()
                    },
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_left),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(19.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // =======================
        // FIRE + STREAK NUMBER
        // =======================

        Box(
            modifier = Modifier
                .size(width = 190.dp, height = 155.dp),
            contentAlignment = Alignment.Center
        ) {

            // Glow
            Box(
                modifier = Modifier
                    .size(145.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                LimeGreen.copy(alpha = 0.45f),
                                LimeGreen.copy(alpha = 0.18f),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    )
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_fire),
                contentDescription = "Streak Fire",
                tint = LimeGreen,
                modifier = Modifier.size(78.dp)
            )

            // Number streak
            Text(
                text = streak.toString(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = DarkCard,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 15.dp, bottom = 5.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.25f),
                        ambientColor = Color.Black.copy(alpha = 0.25f)
                    )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // =======================
        // TITLE
        // =======================

        Text(
            text = "$streak-Day Streak!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DarkCard
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "You're on fire! Keep it going.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(34.dp))

        // =======================
        // WEEKLY PROGRESS
        // =======================

        WeeklyProgressCard(
            completedDays = streak
        )

        Spacer(modifier = Modifier.height(22.dp))

        // =======================
        // BADGE CARD
        // =======================

        WarriorBadgeCard(
            currentDays = streak,
            targetDays = 7
        )

        Spacer(modifier = Modifier.weight(1f))

        // =======================
        // SHARE BUTTON
        // =======================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(LimeGreen)
                .clickable {

                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"

                        putExtra(
                            Intent.EXTRA_TEXT,
                            "🔥 I'm on a $streak-Day Streak! Keep moving!"
                        )
                    }

                    context.startActivity(
                        Intent.createChooser(
                            shareIntent,
                            "Share Milestone"
                        )
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = DarkCard,
                modifier = Modifier.size(21.dp)
            )

            Spacer(modifier = Modifier.size(9.dp))

            Text(
                text = "Share Milestone",
                color = DarkCard,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


// ========================================================
// WEEKLY PROGRESS
// ========================================================

@Composable
private fun WeeklyProgressCard(
    completedDays: Int
) {

    val days = listOf(
        "M",
        "T",
        "W",
        "T",
        "F",
        "S",
        "S"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFD2D6DC))
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )
    ) {

        Text(
            text = "WEEKLY PROGRESS",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            days.forEachIndexed { index, day ->

                DayIndicator(
                    day = day,
                    completed = index < completedDays
                )
            }
        }
    }
}


// ========================================================
// DAY INDICATOR
// ========================================================

@Composable
private fun DayIndicator(
    day: String,
    completed: Boolean
) {

    val backgroundColor =
        if (completed) LimeGreen
        else DarkCard

    val contentColor =
        if (completed) DarkCard
        else Color(0xFF808790)

    Column(
        modifier = Modifier
            .size(
                width = 37.dp,
                height = 60.dp
            )
            .clip(RoundedCornerShape(19.dp))
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = day,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (completed) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(DarkCard)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF555B63))
            )
        }
    }
}


// ========================================================
// 7 DAY WARRIOR BADGE
// ========================================================

@Composable
private fun WarriorBadgeCard(
    currentDays: Int,
    targetDays: Int
) {

    val progress =
        currentDays.toFloat() / targetDays.toFloat()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFD2D6DC))
            .padding(18.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Badge icon
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(
                        LimeGreen.copy(alpha = 0.38f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_award
                    ),
                    contentDescription = "Warrior Badge",
                    tint = LimeGreen,
                    modifier = Modifier.size(29.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Column {

                Text(
                    text = "7-Day Warrior Badge",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkCard
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$currentDays of $targetDays days completed",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGray
                )
            }
        }

        Spacer(modifier = Modifier.height(17.dp))

        // Progress bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(DarkCard)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(8.dp)
                    .background(LimeGreen)
            )
        }
    }
}

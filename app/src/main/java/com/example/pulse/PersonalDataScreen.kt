package com.example.pulse

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray
import java.util.Calendar

@Composable
fun PersonalDataScreen(navController: NavController) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    
    var dateOfBirth by remember { mutableStateOf("Date of Birth") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDay = selectedDay.toString().padStart(2, '0')
            val formattedMonth = (selectedMonth + 1).toString().padStart(2, '0')
            dateOfBirth = "$formattedDay / $formattedMonth / $selectedYear"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
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
        
        // Mid-Right Shape
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = 100.dp, y = 50.dp)
                .width(250.dp)
                .height(100.dp)
                .rotate(-30f)
                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(50))
        )

        // Mid-Left Shape
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = (-80).dp, y = (-50).dp)
                .width(220.dp)
                .height(90.dp)
                .rotate(-30f)
                .background(LimeGreen.copy(alpha = 0.5f), shape = RoundedCornerShape(50))
        )

        // 3. Foreground Content Layer
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                            text = "PROFILE",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = DarkCard,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Header
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(2.dp)
                            .background(LimeGreen)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ABOUT YOU",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = LimeGreen
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Let's make it\npersonal.",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 48.sp,
                    color = DarkCard
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Your details help us shape goals that fit your body and your pace.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Inputs
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Date of Birth
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color(0xFFF3F4F6), shape = RoundedCornerShape(16.dp))
                        .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { datePickerDialog.show() }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Calendar",
                        tint = LimeGreen,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = dateOfBirth,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (dateOfBirth == "Date of Birth") TextGray else DarkCard
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Height & Weight Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Height (cm)", color = TextGray) },
                        leadingIcon = {
                            Icon(painterResource(id = R.drawable.ic_ruler), contentDescription = "Height", tint = LimeGreen, modifier = Modifier.size(20.dp))
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF3F4F6),
                            unfocusedContainerColor = Color(0xFFF3F4F6),
                            focusedBorderColor = LimeGreen,
                            unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                            focusedTextColor = DarkCard,
                            unfocusedTextColor = DarkCard
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Weight (kg)", color = TextGray) },
                        leadingIcon = {
                            Icon(painterResource(id = R.drawable.ic_weight), contentDescription = "Weight", tint = LimeGreen, modifier = Modifier.size(20.dp))
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF3F4F6),
                            unfocusedContainerColor = Color(0xFFF3F4F6),
                            focusedBorderColor = LimeGreen,
                            unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                            focusedTextColor = DarkCard,
                            unfocusedTextColor = DarkCard
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Gender
                Text(
                    text = "GENDER",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val isMale = gender == "Male"
                    val isFemale = gender == "Female"

                    // Male
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .background(
                                color = if (isMale) LimeGreen else Color(0xFFF3F4F6),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isMale) LimeGreen else Color.LightGray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { gender = "Male" },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Male",
                            tint = DarkCard,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Male",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = DarkCard
                        )
                    }

                    // Female
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .background(
                                color = if (isFemale) LimeGreen else Color(0xFFF3F4F6),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isFemale) LimeGreen else Color.LightGray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { gender = "Female" },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Female",
                            tint = DarkCard,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Female",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = DarkCard
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(48.dp))

            // Continue Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(LimeGreen, shape = RoundedCornerShape(32.dp))
                    .clip(RoundedCornerShape(32.dp))
                    .clickable { 
                        navController.navigate("home") { 
                            popUpTo(0) 
                        } 
                    }
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Continue",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
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
        }
    }
}
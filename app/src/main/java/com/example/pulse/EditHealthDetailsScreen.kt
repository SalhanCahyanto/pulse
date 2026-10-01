package com.example.pulse

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.*
import android.app.DatePickerDialog
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun EditHealthDetailsScreen(navController: NavController) {
    // State untuk menyimpan data inputan form
    var birthDate by remember { mutableStateOf("15 / 08 / 1995") }
    var selectedSex by remember { mutableStateOf("Female") }
    var height by remember { mutableStateOf("172") }
    var weight by remember { mutableStateOf("65") }

    // Mendapatkan context untuk menampilkan DatePickerDialog
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    
    // Inisialisasi DatePickerDialog dengan aksi saat tanggal dipilih
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDay = selectedDay.toString().padStart(2, '0')
            val formattedMonth = (selectedMonth + 1).toString().padStart(2, '0')
            birthDate = "$formattedDay / $formattedMonth / $selectedYear"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Container utama layar
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp, bottom = 48.dp)
    ) {
        // UI Bagian Header (Judul Layar)
        Text(
            text = "Health Details",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = DarkCard,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        // Info Card (Pemberitahuan/Info Pengaturan Akurat)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkCard, shape = RoundedCornerShape(16.dp))
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF333333), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_activity),
                    contentDescription = null,
                    tint = LimeGreen,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Accurate tracking setup",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Metrics align step and calorie formulas directly to you.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGray
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Form Input Tanggal Lahir (Menampilkan DatePicker saat diklik)
        Text(
            text = "BIRTH DATE",
            style = MaterialTheme.typography.labelMedium,
            color = TextGray,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(DarkCard)
                // Menampilkan dialog kalender saat baris ini diklik
                .clickable { datePickerDialog.show() }
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = birthDate,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Form Input Jenis Kelamin (Toggles)
        Text(
            text = "GENDER",
            style = MaterialTheme.typography.labelMedium,
            color = TextGray,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkCard, shape = RoundedCornerShape(12.dp))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Status cek apakah Male atau Female sedang dipilih
            val isMale = selectedSex == "Male"
            val isFemale = selectedSex == "Female"
            
            // Tombol Pilihan Pria (Male)
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isMale) LimeGreen else Color.Transparent)
                    .clickable { selectedSex = "Male" }
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    tint = if (isMale) DarkCard else TextGray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Male",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isMale) DarkCard else TextGray
                )
            }
            
            // Tombol Pilihan Wanita (Female)
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isFemale) LimeGreen else Color.Transparent)
                    .clickable { selectedSex = "Female" }
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    tint = if (isFemale) DarkCard else TextGray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Female",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isFemale) DarkCard else TextGray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // UI Form Input Tinggi & Berat Badan (Side-by-side / Sejajar)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Kotak Input Tinggi Badan
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "HEIGHT",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkCard, shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = height,
                        onValueChange = { height = it },
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        cursorBrush = SolidColor(LimeGreen),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "cm",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = LimeGreen
                    )
                }
            }

            // Kotak Input Berat Badan
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "WEIGHT",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkCard, shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        cursorBrush = SolidColor(LimeGreen),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "kg",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = LimeGreen
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // UI Bagian Footer (Informasi Keamanan Data)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Your physiological data is stored securely.",
                style = MaterialTheme.typography.labelSmall,
                color = TextGray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // UI Tombol Simpan (Save Button)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(LimeGreen)
                // Fungsi navigasi untuk kembali ke halaman profile saat diklik
                .clickable { navController.popBackStack() }
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Save Changes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkCard
            )
        }
    }
}
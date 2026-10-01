package com.example.pulse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pulse.ui.theme.DarkCard
import com.example.pulse.ui.theme.LimeGreen
import com.example.pulse.ui.theme.TextGray

// Halaman Utama untuk proses Sign In (Masuk)
@Composable
fun FitnessSignInScreen(navController: NavController) {
    // State lokal untuk menyimpan input email
    var email by remember { mutableStateOf("") }
    // State lokal untuk menyimpan input password
    var password by remember { mutableStateOf("") }
    // State lokal untuk mengatur visibilitas (terlihat/tersembunyi) teks password
    var passwordVisible by remember { mutableStateOf(false) }

    // Container utama layar dengan sistem tumpukan (overlay)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        // 1. Lapisan Latar Belakang Dekoratif (Background Decorative Layer)
        // Lingkaran Hijau di Kanan Atas
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 100.dp, y = (-80).dp)
                .size(350.dp)
                .background(LimeGreen, shape = CircleShape)
        )
        
        // Bentuk Kapsul Abu-abu di Kanan Tengah
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = 100.dp, y = 50.dp)
                .width(250.dp)
                .height(100.dp)
                .rotate(-30f)
                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(50))
        )

        // Bentuk Kapsul Hijau di Kiri Tengah
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = (-80).dp, y = (-50).dp)
                .width(200.dp)
                .height(80.dp)
                .rotate(-30f)
                .background(LimeGreen, shape = RoundedCornerShape(50))
        )

        // 3. Lapisan Konten Utama di Depan (Bisa di-scroll vertikal)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(24.dp)
        ) {
            // UI Bagian Bar Atas (Teks SORA dan Badge MEMBER)
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
                            text = "MEMBER",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = DarkCard,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // UI Bagian Header (Logo Utama, Salam, dan Subteks)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Kotak Logo Utama dengan efek bayangan (shadow)
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(elevation = 24.dp, shape = CircleShape, spotColor = DarkCard, ambientColor = DarkCard)
                        .background(DarkCard, CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_sora),
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Hello.",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DarkCard
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Sign in and keep your rhythm moving.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // UI Bagian Form Input (Email dan Password)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Kolom Input Email
                Text(
                    text = "EMAIL",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("you@example.com", color = TextGray) },
                    leadingIcon = {
                        Icon(Icons.Outlined.Email, contentDescription = "Email", tint = LimeGreen)
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Kolom Input Password
                Text(
                    text = "PASSWORD",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Enter your password", color = TextGray) },
                    leadingIcon = {
                        Icon(Icons.Outlined.Lock, contentDescription = "Password", tint = LimeGreen)
                    },
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Default.Visibility
                        else Icons.Default.VisibilityOff

                        // Tombol ikon (mata) untuk menampilkan atau menyembunyikan teks password
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = "Toggle Password Visibility", tint = TextGray)
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF3F4F6),
                        unfocusedContainerColor = Color(0xFFF3F4F6),
                        focusedBorderColor = LimeGreen,
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                        focusedTextColor = DarkCard,
                        unfocusedTextColor = DarkCard
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // UI Teks Lupa Password (Bisa diklik)
                Text(
                    text = "Forgot Password?",
                    style = MaterialTheme.typography.labelLarge,
                    color = LimeGreen,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.End)
                        // Fungsi/logika aksi saat teks Lupa Password diklik
                        .clickable { /* Handle forgot password */ }
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // UI Bagian Tombol Aksi Bawah
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Tombol Login Utama
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(LimeGreen, shape = RoundedCornerShape(32.dp))
                        .clip(RoundedCornerShape(32.dp))
                        // Fungsi navigasi: Berpindah ke halaman personal_data setelah login berhasil
                        .clickable { navController.navigate("personal_data") }
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Login",
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

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Login dengan Google
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(Color(0xFF4A4E59), shape = RoundedCornerShape(32.dp))
                        .clip(RoundedCornerShape(32.dp))
                        // Logika untuk aksi login Google
                        .clickable { /* Handle Google Sign In */ }
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle, // Placeholder for Google icon
                        contentDescription = "Google",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Sign in with Google",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    }
}
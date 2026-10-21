package com.example.leafai.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable

@Composable
fun InfoScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Informasi Aplikasi",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "← Kembali ke Deteksi",
            color = Color(0xFF159447),
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                onBackClick()
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        InfoCard(
            title = "Tentang LeafyAI",
            content = "LeafyAI adalah aplikasi deteksi penyakit pada daun cabai menggunakan kamera realtime dan model CNN berbasis TensorFlow Lite."
        )

        InfoCard(
            title = "Cara Penggunaan",
            content = "Arahkan kamera ke daun cabai. Tunggu hasil prediksi muncul secara realtime, lalu klik saran penanganan untuk melihat detail hasil deteksi."
        )

        InfoCard(
            title = "Fitur Aplikasi",
            content = "Deteksi realtime, hasil prediksi, saran penanganan, penyimpanan riwayat, dan detail hasil deteksi."
        )

        InfoCard(
            title = "Developer",
            content = "Dikembangkan oleh Ayu Suningsih\nProgram Studi Teknik Informatika"
        )

        InfoCard(
            title = "Versi",
            content = "LeafyAI v1.0"
        )
    }
}

@Composable
private fun InfoCard(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .background(Color.White, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF159447)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = content,
            fontSize = 15.sp,
            color = Color.DarkGray
        )
    }
}
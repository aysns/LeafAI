package com.example.leafai.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafai.history.DetectionHistory
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import android.graphics.BitmapFactory

@Composable
fun HistoryDetailScreen(
    history: DetectionHistory?,
    onBackClick: () -> Unit
) {

    val scrollState = rememberScrollState()

    if (history == null) {
        Text("Data riwayat tidak ditemukan")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Detail Riwayat",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color.LightGray, RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (history.imagePath != null) {

                val bitmap = BitmapFactory.decodeFile(history.imagePath)

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Gambar Riwayat",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("Preview Gambar", color = Color.Gray)
                }

            } else {
                Text("Preview Gambar", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ResultItem("Jenis Penyakit", history.diseaseName)
        ResultItem("Akurasi", history.confidence)
        ResultItem("Deskripsi", history.description)
        ResultItem("Saran Penanganan", history.suggestion)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(
                text = "Kembali",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}
package com.example.leafai.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.leafai.history.HistoryState
import androidx.compose.foundation.clickable
import android.graphics.BitmapFactory

@Composable
fun HistoryScreen(
    onItemClick: () -> Unit,
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
            text = "Riwayat Deteksi",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "← Kembali ke Deteksi",
            color = Color(0xFF159447),
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                onBackClick()
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (HistoryState.historyList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Belum ada riwayat deteksi",
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(HistoryState.historyList) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                HistoryState.selectedHistory = item

                                onItemClick()
                            }
                            .background(
                                Color.White,
                                RoundedCornerShape(18.dp)
                            )
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(
                                    Color.LightGray,
                                    RoundedCornerShape(14.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (item.imagePath != null) {

                                val bitmap = BitmapFactory.decodeFile(item.imagePath)

                                if (bitmap != null) {
                                    Image(
                                        bitmap = bitmap.asImageBitmap(),
                                        contentDescription = "Riwayat Gambar",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    Text("No Image", fontSize = 11.sp)
                                }

                            } else {
                                Text("No Image", fontSize = 11.sp)
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = item.diseaseName,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Akurasi: ${item.confidence}",
                                fontSize = 14.sp,
                                color = Color(0xFF159447)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = item.suggestion,
                                fontSize = 13.sp,
                                color = Color.Gray,
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }
    }
}
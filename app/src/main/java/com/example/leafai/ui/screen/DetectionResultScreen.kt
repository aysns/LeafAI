package com.example.leafai.ui.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafai.ui.model.PredictionInfo
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.example.leafai.history.DetectionHistory
import com.example.leafai.history.HistoryState
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import com.example.leafai.history.ImageStorage

@Composable
fun DetectionResultScreen(
    info: PredictionInfo,
    imageBitmap: Bitmap?,
    onBackClick: () -> Unit,
    onDetectAgainClick: () -> Unit
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Hasil Deteksi",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Color.LightGray,
                    RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if(imageBitmap != null) {
                Image(
                    bitmap = imageBitmap.asImageBitmap(),
                    contentDescription = "Hasil Deteksi",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = "Preview Gambar",
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ResultItem(
            title = "Jenis Penyakit",
            value = info.title
        )

        ResultItem(
            title = "Akurasi",
            value = info.confidence
        )

        ResultItem(
            title = "Deskripsi",
            value = info.description
        )

        ResultItem(
            title = "Saran Penanganan",
            value = info.suggestion
        )

//        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    val imagePath = ImageStorage.saveBitmapToInternalStorage(
                        context = context,
                        bitmap = imageBitmap
                    )

                    HistoryState.addHistory(
                        context = context,
                        DetectionHistory(
//                          image = imageBitmap,
                            imagePath = imagePath,
                            diseaseName = info.title,
                            confidence = info.confidence,
                            description = info.description,
                            suggestion = info.suggestion
                        )
                    )
                    Toast.makeText(
                        context,
                        "Riwayat berhasil disimpan",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF159447)
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    text = "Simpan Riwayat",
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = onDetectAgainClick,
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    text = "Deteksi Ulang",
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ResultItem(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .background(
                Color.White,
                RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            fontSize = 17.sp,
            color = Color.Black
        )
    }
}
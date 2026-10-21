package com.example.leafai.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafai.ui.component.PredictionCard
import com.example.leafai.ui.component.SuggestionCard
import com.example.leafai.ui.model.mapPrediction
import com.example.leafai.ui.component.ScannerFrame
import com.example.leafai.ui.component.BottomNavigationBar

@Composable
fun CameraScreen(
    onSuggestionClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    val info = mapPrediction(PredictionState.resultText)

    Box(modifier = Modifier.fillMaxSize()) {

        CameraPreview(modifier = Modifier.fillMaxSize())

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.18f))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Deteksi Penyakit",
                color = Color.White,
                fontSize = 28.sp
            )

            Text(
                text = "Tanaman Cabai",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .background(
                    Color.Black.copy(alpha = 0.45f),
                    RoundedCornerShape(30.dp)
                )
                .padding(horizontal = 18.dp, vertical = 8.dp)
        ) {
            Text(
                text = "● LIVE   Realtime",
                color = Color.White,
                fontSize = 15.sp
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 130.dp)
        ) {
            PredictionCard(info = info)
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 24.dp, end = 24.dp, bottom = 90.dp)
        ) {
            SuggestionCard(
                suggestion = info.suggestion,
                onClick = {
                    if (
                        info.title != "Menunggu Prediksi" &&
                        info.title != "Bukan Daun"
                    ) {
                        onSuggestionClick()
//                    if (info.title != "Menunggu Prediksi") {
//                        onSuggestionClick()
                    }
                }
            )
        }

        ScannerFrame(
            modifier = Modifier.align(Alignment.Center)
        )

        BottomNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHistoryClick = onHistoryClick,
            onInfoClick = onInfoClick
        )
    }
}
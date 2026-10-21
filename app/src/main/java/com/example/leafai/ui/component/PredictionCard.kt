package com.example.leafai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafai.ui.model.PredictionInfo

@Composable
fun PredictionCard(info: PredictionInfo) {
    Column(
        modifier = Modifier
            .width(250.dp)
            .background(
                Color.Black.copy(alpha = 0.55f),
                RoundedCornerShape(20.dp)
            )
            .padding(18.dp)
    ) {
        Text(
            text = "Prediksi",
            color = Color(0xFF68D84F),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = info.title,
            color = Color.White,
            fontSize = 24.sp
        )

        Text(
            text = "(${info.latinName})",
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Akurasi: ${info.confidence}",
            color = Color.White,
            fontSize = 20.sp
        )
    }
}
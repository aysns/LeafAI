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
import androidx.compose.foundation.clickable

@Composable
fun SuggestionCard(
    suggestion: String,
    onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .background(
                Color.Black.copy(alpha = 0.55f),
                RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Saran Penanganan",
            color = Color(0xFF68D84F),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = suggestion,
            color = Color.White,
            fontSize = 15.sp
        )
    }
}
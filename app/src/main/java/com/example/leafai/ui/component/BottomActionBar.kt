package com.example.leafai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomActionBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 34.dp, end = 34.dp, bottom = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleActionButton(
            icon = "🖼",
            label = "Galeri"
        )

        ShutterButton()

        CircleActionButton(
            icon = "↺",
            label = "Riwayat"
        )
    }
}

@Composable
private fun CircleActionButton(
    icon: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    Color.Black.copy(alpha = 0.55f),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = icon,
                color = Color.White,
                fontSize = 26.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun ShutterButton() {
    Box(
        modifier = Modifier
            .size(86.dp)
            .background(Color.White, CircleShape)
            .border(
                width = 5.dp,
                color = Color.White.copy(alpha = 0.85f),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(
                    width = 3.dp,
                    color = Color(0xFF1B5E20),
                    shape = CircleShape
                )
        )
    }
}
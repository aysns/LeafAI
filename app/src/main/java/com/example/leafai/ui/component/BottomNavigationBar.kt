package com.example.leafai.ui.component

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
import androidx.compose.foundation.clickable

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    onHistoryClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(92.dp)
            .background(
                Color.White.copy(alpha = 0.96f),
                RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
            )
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            icon = "▣",
            label = "Deteksi",
            selected = true
        )

        BottomNavItem(
            icon = "◷",
            label = "Riwayat",
            selected = false,
            onClick = onHistoryClick
        )

        BottomNavItem(
            icon = "ⓘ",
            label = "Informasi",
            selected = false,
            onClick = onInfoClick
        )
    }
}

@Composable
private fun BottomNavItem(
    icon: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit = {}
) {
    val color = if (selected) Color(0xFF159447) else Color.Gray

    Column(
        modifier = Modifier.clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            color = color,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = color,
            fontSize = 14.sp
        )
    }
}
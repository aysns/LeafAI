package com.example.leafai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScannerFrame(
    modifier: Modifier = Modifier
) {
    val green = Color(0xFF68FF5A)

    Box(
        modifier = modifier.size(280.dp)
    ) {

        // kiri atas
        Box(
            Modifier
                .align(Alignment.TopStart)
                .width(50.dp)
                .height(5.dp)
                .background(green)
        )

        Box(
            Modifier
                .align(Alignment.TopStart)
                .width(5.dp)
                .height(50.dp)
                .background(green)
        )

        // kanan atas
        Box(
            Modifier
                .align(Alignment.TopEnd)
                .width(50.dp)
                .height(5.dp)
                .background(green)
        )

        Box(
            Modifier
                .align(Alignment.TopEnd)
                .width(5.dp)
                .height(50.dp)
                .background(green)
        )

        // kiri bawah
        Box(
            Modifier
                .align(Alignment.BottomStart)
                .width(50.dp)
                .height(5.dp)
                .background(green)
        )

        Box(
            Modifier
                .align(Alignment.BottomStart)
                .width(5.dp)
                .height(50.dp)
                .background(green)
        )

        // kanan bawah
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .width(50.dp)
                .height(5.dp)
                .background(green)
        )

        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .width(5.dp)
                .height(50.dp)
                .background(green)
        )
    }
}
package com.example.leafai.camera

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

object PredictionState {
    var resultText by mutableStateOf("Menunggu prediksi.....")
    var lastBitmap by mutableStateOf<Bitmap?>(null)
}
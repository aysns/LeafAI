package com.example.leafai.history

data class DetectionHistory(

    val imagePath: String?,
    val diseaseName: String,
    val confidence: String,
    val description: String,
    val suggestion: String,
    val timestamp: Long = System.currentTimeMillis()
)
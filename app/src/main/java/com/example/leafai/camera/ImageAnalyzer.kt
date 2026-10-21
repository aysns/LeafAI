package com.example.leafai.camera

import android.content.Context
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.leafai.ml.TFLiteHelper
import com.example.leafai.utils.ImageUtils

class ImageAnalyzer(private val context: Context) : ImageAnalysis.Analyzer {

    private var frameCount = 0
    private val model = TFLiteHelper(context)

    override fun analyze(image: ImageProxy) {
        frameCount++

        if (frameCount % 30 == 0) {
            try {
                val bitmap = ImageUtils.imageProxyToBitmap(image)
                val cropped = ImageUtils.cropCenter(bitmap)
//                PredictionState.lastBitmap = bitmap
                  PredictionState.lastBitmap = cropped

                val resized = ImageUtils.resizeBitmap(cropped, 150, 150)

                val input = ImageUtils.bitmapToInputArray(resized)

                val (label, confidence) = model.predict(input)

                android.util.Log.d(
                    "PREDICT_CHECK",
                    "label=$label confidence=${confidence * 100}"
                )

                if (confidence < 0.0f) {

                    PredictionState.resultText =
                        "Tidak terdeteksi (0%)"

                } else {

                    PredictionState.resultText =
                        "$label (${String.format("%.2f", confidence * 100)}%)"
                }

            } catch (e: Exception) {
                PredictionState.resultText = "Error: ${e.message}"
            }
        }

        image.close()
    }
}
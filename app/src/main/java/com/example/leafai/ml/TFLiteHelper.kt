package com.example.leafai.ml

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TFLiteHelper(context: Context) {

    private val interpreter: Interpreter

    private val labels = listOf(
        "bercak daun",
        "bukan daun",
        "daun keriting",
        "daun menguning",
        "daun sehat"
    )

    init {
        interpreter = Interpreter(loadModelFile(context))
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("v16model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset,
            declaredLength
        )
    }

    fun predict(input: Array<Array<Array<FloatArray>>>): Pair<String, Float> {
        val output = Array(1) { FloatArray(5) }

        interpreter.run(input, output)

        val scores = output[0]

        android.util.Log.d(
            "RAW_OUTPUT",
            scores.joinToString()
        )

        var maxIndex = 0
        var maxScore = scores[0]

        for (i in scores.indices) {
            if (scores[i] > maxScore) {
                maxIndex = i
                maxScore = scores[i]
            }
        }

        return Pair(labels[maxIndex], maxScore)
    }
}


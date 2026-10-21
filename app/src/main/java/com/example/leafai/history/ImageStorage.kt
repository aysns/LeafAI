package com.example.leafai.history

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

object ImageStorage {

    fun saveBitmapToInternalStorage(
        context: Context,
        bitmap: Bitmap?
    ): String? {

        if (bitmap == null) return null

        val fileName = "history_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        FileOutputStream(file).use { outputStream ->
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                90,
                outputStream
            )
        }

        return file.absolutePath
    }
}
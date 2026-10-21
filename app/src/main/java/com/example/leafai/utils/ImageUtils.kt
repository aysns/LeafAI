package com.example.leafai.utils

import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.graphics.Matrix

object ImageUtils {

    fun imageProxyToBitmap(image: ImageProxy): Bitmap {

        val yBuffer = image.planes[0].buffer
        val uBuffer = image.planes[1].buffer
        val vBuffer = image.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvImage = YuvImage(
            nv21,
            ImageFormat.NV21,
            image.width,
            image.height,
            null
        )

        val out = ByteArrayOutputStream()

        yuvImage.compressToJpeg(
            Rect(0, 0, image.width, image.height),
            100,
            out
        )

//        val imageBytes = out.toByteArray()
//
//        return android.graphics.BitmapFactory.decodeByteArray(
//            imageBytes,
//            0,
//            imageBytes.size
//        )

        val imageBytes = out.toByteArray()

        val bitmap = BitmapFactory.decodeByteArray(
            imageBytes,
            0,
            imageBytes.size
        )

        val matrix = Matrix().apply {
            postRotate(image.imageInfo.rotationDegrees.toFloat())
        }

        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }

    fun cropCenter(bitmap: Bitmap): Bitmap {
        val cropSize = minOf(bitmap.width, bitmap.height)

        val x = (bitmap.width - cropSize) / 2
        val y = (bitmap.height - cropSize) / 2

        return Bitmap.createBitmap(
            bitmap,
            x,
            y,
            cropSize,
            cropSize
        )
    }

    fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    fun bitmapToInputArray(bitmap: Bitmap): Array<Array<Array<FloatArray>>> {

        val input = Array(1) {
            Array(150) {
                Array(150) {
                    FloatArray(3)
                }
            }
        }

        for (y in 0 until 150) {
            for (x in 0 until 150) {

                val pixel = bitmap.getPixel(x, y)

                val r = ((pixel shr 16) and 0xFF) / 255f
                val g = ((pixel shr 8) and 0xFF) / 255f
                val b = (pixel and 0xFF) / 255f

                input[0][y][x][0] = r
                input[0][y][x][1] = g
                input[0][y][x][2] = b
            }
        }

        return input
    }
}
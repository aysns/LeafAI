package com.example.leafai.camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun CameraPermission(
    onPermissionGranted: @Composable () -> Unit
) {
    val context = LocalContext.current

    var hasPermission by remember { mutableStateOf(false) }

    // fungsi untuk cek ulang permission
    fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    // cek permission setiap composable ini tampil
    LaunchedEffect(Unit) {
        hasPermission = checkPermission()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }

    if (hasPermission) {
        onPermissionGranted()
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Camera permission diperlukan untuk menjalankan aplikasi.")
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    launcher.launch(Manifest.permission.CAMERA)
                }) {
                    Text("Izinkan Kamera")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = {
                    hasPermission = checkPermission()
                }) {
                    Text("Cek Ulang Permission")
                }
            }
        }
    }
}
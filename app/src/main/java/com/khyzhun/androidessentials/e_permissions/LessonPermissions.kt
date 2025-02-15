package com.khyzhun.androidessentials.e_permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.khyzhun.androidessentials._common.theme.DefaultTheme

class LessonPermissions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultTheme {
                CameraPermissionScreen()
            }
        }
    }
}

@Composable
fun CameraPermissionScreen() {
    val context = LocalContext.current
    val cameraPermission = Manifest.permission.CAMERA

    /********************************************************************
     * 1. Callback for permission launcher
     ******************************************************************/

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Callback: Permission granted!", Toast.LENGTH_SHORT).show()
        } else {
            val activity = context as? android.app.Activity
            if (activity != null && !ActivityCompat.shouldShowRequestPermissionRationale(activity, cameraPermission)) {
                Toast.makeText(context, "Permission denied for ever", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Callback: Permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /********************************************************************
     * 2. Simple UI
     ******************************************************************/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Let's check camera permission")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            /***************************************************************
             * 3. Instant checking permission for Camera
             **************************************************************/
            when {
                ContextCompat.checkSelfPermission(
                    context,
                    cameraPermission
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(
                        context,
                        "Permission already granted! Thank you!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    requestPermissionLauncher.launch(cameraPermission)
                }
            }
        }) {
            Text("Let's do it")
        }
    }
}
















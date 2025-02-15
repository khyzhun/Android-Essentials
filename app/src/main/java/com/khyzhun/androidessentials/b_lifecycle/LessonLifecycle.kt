package com.khyzhun.androidessentials.b_lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.khyzhun.androidessentials._common.theme.EmptyView

class LessonLifecycle : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "onCreate")
        enableEdgeToEdge()
        setContent {
            EmptyView()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy")
    }
}

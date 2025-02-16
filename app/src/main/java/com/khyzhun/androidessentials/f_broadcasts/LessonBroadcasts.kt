package com.khyzhun.androidessentials.f_broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khyzhun.androidessentials._common.theme.DefaultTheme

/**
 * Example 1
 */
class LessonBroadcasts : ComponentActivity() {

    private lateinit var powerReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        powerReceiver = PowerConnectedReceiver()
        setContent {
            DefaultTheme {
                /**
                 * Example 2
                 */
                //PowerReceiverScreen()
                /**
                 * Example 3
                 */
                //CustomBroadcastSendScreen()
                /**
                 * Example 4
                 */
                //CustomBroadcastReceiveScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(powerReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(powerReceiver)
    }


    private class PowerConnectedReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                Intent.ACTION_POWER_CONNECTED -> {
                    Toast.makeText(context, "Charging...!", Toast.LENGTH_SHORT).show()
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    Toast.makeText(context, "Charging is disconnected!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
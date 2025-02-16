package com.khyzhun.androidessentials.f_broadcasts

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun PowerReceiverScreen() {
    val context = LocalContext.current
    var message by remember { mutableStateOf("Waiting for the action...") }

    // Creating BroadcastReceiver
    val powerReceiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                message = when (intent.action) {
                    Intent.ACTION_POWER_CONNECTED -> "Battery charging! 🔋🔋🔋"
                    Intent.ACTION_POWER_DISCONNECTED -> "Battery is not charging...🪫"
                    else -> "Default state"
                }
            }
        }
    }

    // Registering receiver on the launch
    LaunchedEffect(Unit) {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        context.registerReceiver(powerReceiver, filter)
    }

    // Unregistering receiver on the exit
    DisposableEffect(Unit) {
        onDispose {
            context.unregisterReceiver(powerReceiver)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* do something cool */ }) {
            Text("Amazing button")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PowerReceiverScreenPreview() {
    PowerReceiverScreen()
}































const val CUSTOM_ACTION = "com.example.CUSTOM_BROADCAST"

@Composable
fun CustomBroadcastSendScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val intent = Intent(CUSTOM_ACTION)
                intent.putExtra("message", "Hello from Custom Broadcast!")
                intent.component = ComponentName(
                    context.packageName,
                    CustomBroadcastReceiver::class.java.name
                )
                context.sendBroadcast(intent)
            }
        ) {
            Text("Send Custom Broadcast")
        }
    }
}

class CustomBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == CUSTOM_ACTION) {
            val message = intent.getStringExtra("message") ?: "No message received"
            Toast.makeText(context, "Received: $message", Toast.LENGTH_SHORT).show()
        }
    }
}

































@Composable
fun CustomBroadcastReceiveScreen() {
    val context = LocalContext.current
    var receivedMessage by remember { mutableStateOf("Waiting for broadcast...") }

    // Create the BroadcastReceiver
    val receiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == CUSTOM_ACTION) {
                    val message = intent.getStringExtra("message") ?: "No message received"
                    receivedMessage = message
                }
            }
        }
    }

    // Register the receiver dynamically

    LaunchedEffect(Unit) {
        val filter = IntentFilter(CUSTOM_ACTION)
        ContextCompat.registerReceiver(context, receiver, filter, ContextCompat.RECEIVER_EXPORTED)
    }

    // Unregister the receiver dynamically
    DisposableEffect(Unit) {
        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Received broadcast: $receivedMessage")
    }
}
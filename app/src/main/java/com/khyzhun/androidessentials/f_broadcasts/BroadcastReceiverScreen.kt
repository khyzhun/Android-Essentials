package com.khyzhun.androidessentials.f_broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun PowerReceiverScreen() {
    val context = LocalContext.current
    var message by remember { mutableStateOf("Очікуємо подію...") }

    // Окремо створюємо BroadcastReceiver
    val powerReceiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                message = when (intent.action) {
                    Intent.ACTION_POWER_CONNECTED -> "Зарядка підключена!"
                    Intent.ACTION_POWER_DISCONNECTED -> "Зарядка відключена!"
                    else -> "Невідома подія"
                }
            }
        }
    }

    // Підключаємо ресівер при появі Composable
    LaunchedEffect(Unit) {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        context.registerReceiver(powerReceiver, filter)
    }

    // Відключаємо ресівер при виході з Composable
    DisposableEffect(Unit) {
        onDispose {
            context.unregisterReceiver(powerReceiver)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Очікуємо події */ }) {
            Text("Очікуємо події")
        }
    }
}
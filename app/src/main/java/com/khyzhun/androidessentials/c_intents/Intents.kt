package com.khyzhun.androidessentials.c_intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.khyzhun.androidessentials._common.theme.EmptyView

class ExplicitIntentAActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick = {
                val intent = Intent(this, ExplicitIntentBActivity::class.java)
                startActivity(intent)
            }) {
                Text("Open Activity B")
            }
        }
    }
}

class ExplicitIntentBActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyView()
        }
    }
}

class ImplicitIntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW)
                    .apply { data = Uri.parse("https://www.example.com") }

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    // handle your error.
                }
            }) {
                Text("Click")
            }
        }
    }
}

class IntentSenderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick = {
               val intent = Intent(this, IntentReceiverActivity::class.java)
                intent.putExtra("my_key", "very important information")
                startActivity(intent)
            }) {
                Text("Click")
            }
        }
    }
}

class IntentReceiverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick = {
                val data = this.intent.extras?.getString("my_key")
            }) {
                Text("Click")
            }
        }
    }
}


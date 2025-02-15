package com.khyzhun.androidessentials.d_context

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.khyzhun.androidessentials.R
import com.khyzhun.androidessentials._common.theme.EmptyView
import com.khyzhun.androidessentials.a_activity.LessonActivity

class LessonContext : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { EmptyView() }
    }
}

@Composable
fun ShowToastExample() {
    val context = LocalContext.current

    Button(onClick = {
        Toast.makeText(context, "Hello from Compose!", Toast.LENGTH_SHORT).show()
    }) {
        Text("Show Toast")
    }
}

@Composable
fun StringResourceExample() {
    val context = LocalContext.current
    val myString = context.getString(R.string.app_name)

    Text(text = myString)
}

@Composable
fun ColorResourceExample() {
    val context = LocalContext.current
    val myColor = ContextCompat.getColor(context, R.color.purple_200)

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color(myColor))
    )
}

@Composable
fun OpenActivityExample() {
    val context = LocalContext.current

    Button(onClick = {
        val intent = Intent(context, LessonActivity::class.java)
        context.startActivity(intent)
    }) {
        Text("Open Second Activity")
    }
}
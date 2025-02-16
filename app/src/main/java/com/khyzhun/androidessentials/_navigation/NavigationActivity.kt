package com.khyzhun.androidessentials._navigation

import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khyzhun.androidessentials._common.theme.DefaultTheme
import com.khyzhun.androidessentials.a_activity.LessonActivity
import com.khyzhun.androidessentials.b_lifecycle.LessonLifecycle
import com.khyzhun.androidessentials.c_intents.LessonIntents
import com.khyzhun.androidessentials.d_context.LessonContext
import com.khyzhun.androidessentials.e_permissions.LessonPermissions
import com.khyzhun.androidessentials.f_broadcasts.LessonBroadcasts

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultTheme {
                LessonsContent()
            }
        }
    }

    @Composable
    private fun LessonsContent() {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            item { LessonButton("Activity", LessonActivity::class.java) }
            item { LessonButton("Lifecycle", LessonLifecycle::class.java) }
            item { LessonButton("Intents", LessonIntents::class.java) }
            item { LessonButton("Context", LessonContext::class.java) }
            item { LessonButton("Permissions", LessonPermissions::class.java) }
            item { LessonButton("Broadcasts", LessonBroadcasts::class.java) }
        }
    }

    @Composable
    private fun LessonButton(title: String, clazz: Class<*>) {
        Button(
            onClick = {
                startActivity(Intent(this, clazz))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(title)
        }
    }
}
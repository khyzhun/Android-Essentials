package com.khyzhun.androidessentials.g_alert_dialogs

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khyzhun.androidessentials.R
import com.khyzhun.androidessentials._common.theme.DefaultTheme

class LessonAlertDialogs : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_alert_dialogs)

        val button = findViewById<Button>(R.id.buttonClickOnMe)
        button.setOnClickListener {
            showAlertDialog()
        }

        /*
        // Compose Example.
        setContent {
            DefaultTheme {
                SimpleAlertDialog()
            }
        }
        */
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Simple Dialog")
            .setMessage("This is a simple AlertDialog in classic Android.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}
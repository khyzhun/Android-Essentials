package com.khyzhun.androidessentials.g_alert_dialogs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SimpleAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Button(onClick = { showDialog = true }) {
        Text("Show AlertDialog")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Simple Dialog") },
            text = { Text("This is a simple AlertDialog in Jetpack Compose.") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ListAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Button(onClick = { showDialog = true }) {
        Text("Show List Dialog")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Choose an option") },
            text = {
                Column {
                    options.forEach { option ->
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text(option)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun RadioAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Button(onClick = { showDialog = true }) {
        Text("Show Radio Dialog")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Choose one") },
            text = {
                Column {
                    options.forEach { option ->
                        Row {
                            RadioButton(
                                selected = option == selectedOption,
                                onClick = { selectedOption = option }
                            )
                            Text(option)
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun CheckboxAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedOptions by remember { mutableStateOf(setOf<String>()) }
    val options = listOf("Option A", "Option B", "Option C")

    Button(onClick = { showDialog = true }) {
        Text("Show Checkbox Dialog")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Select multiple options") },
            text = {
                Column {
                    options.forEach { option ->
                        Row {
                            Checkbox(
                                checked = selectedOptions.contains(option),
                                onCheckedChange = {
                                    selectedOptions = if (it) {
                                        selectedOptions + option
                                    } else {
                                        selectedOptions - option
                                    }
                                }
                            )
                            Text(option)
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun CustomInputDialog() {
    var showDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Button(onClick = { showDialog = true }) {
        Text("Show Input Dialog")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Enter your name") },
            text = {
                Column {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Name") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    Log.d("CustomInputDialog", "User entered: $text")
                    showDialog = false
                }) {
                    Text("Submit")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewSimpleAlertDialog() {
    SimpleAlertDialog()
}
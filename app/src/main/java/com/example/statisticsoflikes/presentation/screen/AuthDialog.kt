package com.example.statisticsoflikes.presentation.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.statisticsoflikes.untils.Contract.Keys.PASSWORD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordDialog(
    onDismiss: () -> Unit,
    onPasswordEntered: (String) -> Unit
) {

    var password by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Введите пароль") },
        text = {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (password == PASSWORD) {
                        onPasswordEntered(password)
                    } else {
                        password = ""
                        onDismiss()
                    }
                }
            ) {
                Text("OK")
            }
        }
    )
}

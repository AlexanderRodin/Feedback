package com.example.statisticsoflikes.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.statisticsoflikes.R
import com.example.statisticsoflikes.ui.theme.Orange70

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    contactPhone: String,
    onContactPhoneChange: (String) -> Unit,
    textInput: String,
    onTextInputChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.dialog_title_dislike_dialog_screen),
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    color = Orange70,
                    modifier = Modifier
                        .padding(3.dp)
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = contactPhone,
                    onValueChange = {
                        onContactPhoneChange(it)
                    },
                    label = { Text(stringResource(R.string.contact_phone_dislike_dialog_screen)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next // Установите ImeAction на Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            // Переключение на следующее поле при нажатии "Next"
                            keyboardController?.hide()
                        },
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = textInput,
                    onValueChange = {
                        onTextInputChange(it)
                    },
                    label = { Text(stringResource(R.string.text_review_dislike_dialog_screen)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide() // Скрыть клавиатуру после ввода в поле Текст
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(8.dp)
            ) {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                ) {
                    Text("Cancel")
                }

                TextButton(
                    onClick = onOkClick,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("OK")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDisLikeDialogScreenContent() {
    CustomDialog(
        onDismiss = { },
        onOkClick = { },
        contactPhone = "",
        onContactPhoneChange = {},
        textInput = "",
        onTextInputChange = {}
    )
}
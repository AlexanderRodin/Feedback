package com.example.statisticsoflikes.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.statisticsoflikes.R
import com.example.statisticsoflikes.navigation.NavRout
import com.example.statisticsoflikes.presentation.MainViewModel
import com.example.statisticsoflikes.ui.theme.Green70
import com.example.statisticsoflikes.ui.theme.Orange70
import com.example.statisticsoflikes.ui.theme.Red70
import com.example.statisticsoflikes.untils.Contract.Keys.DISLIKE
import com.example.statisticsoflikes.untils.Contract.Keys.LIKE
import com.example.statisticsoflikes.untils.Contract.Keys.PASSWORD


@Composable
fun LikesScreen(viewModel: MainViewModel, navController: NavHostController) {

    ShowStatistic(viewModel, navController)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.dns_logo),
            contentDescription = null,
            modifier = Modifier
        )
        Text(
            text = stringResource(R.string.screen_text),
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            color = Orange70,
            modifier = Modifier
                .padding(10.dp)
        )
        DisLikeButtons(viewModel)
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun ShowStatistic(viewModel: MainViewModel, navController: NavHostController) {

    var expanded by remember { mutableStateOf(false) }
    var isPasswordListDialogOpen by remember { mutableStateOf(false) }
    var isPasswordClearDialogOpen by remember { mutableStateOf(false) }
    var isListPageOpen by remember { mutableStateOf(false) }
    var isInvalidPasswordError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
                },
                text = { Text(text = "Статистика") },
                onClick = {
                    isPasswordListDialogOpen = true
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Clear, contentDescription = null)
                },
                text = { Text(text = "Очистить статистику") },
                onClick = {
                    isPasswordClearDialogOpen = true
                }
            )
        }

        if (isPasswordListDialogOpen) {
            PasswordDialog(
                onDismiss = {
                    expanded = false
                    isPasswordListDialogOpen = false
                },
                onPasswordEntered = { enteredPassword ->
                    if (enteredPassword == PASSWORD) {
                        isListPageOpen = true
                        isPasswordListDialogOpen = false
                    } else {
                        isInvalidPasswordError = true
                        isPasswordListDialogOpen = false
                    }
                }
            )
        }

        if (isPasswordClearDialogOpen) {
            PasswordDialog(
                onDismiss = {
                    expanded = false
                    isPasswordClearDialogOpen = false
                },
                onPasswordEntered = { enteredPassword ->
                    if (enteredPassword == PASSWORD) {
                        viewModel.clearAllDataPreferences()
                        expanded = false
                        isPasswordClearDialogOpen = false
                    } else {
                        isInvalidPasswordError = true
                        expanded = false
                        isPasswordClearDialogOpen = false
                    }
                }
            )
        }

        if (isListPageOpen) {
            navController.navigate(route = NavRout.ListPage.route)
        }

        if (isInvalidPasswordError) {
            Log.d("isInvalidPasswordError", "incorrect password entered")
        }
    }
}

@Composable
fun DisLikeButtons(viewModel: MainViewModel) {

    var isLikeDialogOpen by remember { mutableStateOf(false) }

    var isDisLikeDialogOpen by remember { mutableStateOf(false) }

    var contactPhone by remember { mutableStateOf("") }

    var textInput by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedCard(
            modifier = Modifier
                .clickable {
                    isLikeDialogOpen = true
                    viewModel.clickBtn(LIKE, "", "")
                },
            border = BorderStroke(2.dp, Orange70)
        ) {
            Icon(
                modifier = Modifier.padding(10.dp),
                painter = painterResource(R.drawable.baseline_thumb_up_150),
                contentDescription = "Like",
                tint = Green70
            )
        }

        if (isLikeDialogOpen) {
            LikeDialog(
                onDismiss = { isLikeDialogOpen = false },
            )
        }

        Spacer(modifier = Modifier.width(200.dp))

        OutlinedCard(
            modifier = Modifier
                .clickable {
                    isDisLikeDialogOpen = true
                },
            border = BorderStroke(2.dp, Orange70)
        ) {
            Icon(
                modifier = Modifier.padding(10.dp),
                painter = painterResource(R.drawable.baseline_thumb_down_150),
                contentDescription = "Dislike",
                tint = Red70
            )
        }
        if (isDisLikeDialogOpen) {
            CustomDialog(
                onDismiss = {
                    contactPhone = ""
                    textInput = ""
                    viewModel.clickBtn(DISLIKE, contactPhone, textInput)
                    isDisLikeDialogOpen = false
                },
                onOkClick = {
                    viewModel.clickBtn(DISLIKE, contactPhone, textInput)
                    contactPhone = ""
                    textInput = ""
                    isDisLikeDialogOpen = false
                },
                contactPhone = contactPhone,
                onContactPhoneChange = { contactPhone = it },
                textInput = textInput,
                onTextInputChange = { textInput = it }
            )
        }
    }
}

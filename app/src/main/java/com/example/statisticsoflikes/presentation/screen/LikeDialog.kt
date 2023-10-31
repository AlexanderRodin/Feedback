package com.example.statisticsoflikes.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.statisticsoflikes.R
import com.example.statisticsoflikes.ui.theme.Orange70
import kotlinx.coroutines.delay

@Composable
fun LikeDialog(
    onDismiss: () -> Unit,
) {

    var isDialogOpen by remember { mutableStateOf(true) }

    LaunchedEffect(isDialogOpen) {
        delay(1000)
        onDismiss()
    }

    AlertDialog(
        onDismissRequest = {
            isDialogOpen = false
            onDismiss()
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                GifImage(
                    url = "your_gif_url_here",
                    modifier = Modifier.size(200.dp)
                )
            }
        },
        text = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.like_dialog_text),
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = Orange70,
                    modifier = Modifier
                        .padding(3.dp)

                )
            }
        },
        confirmButton = { }
    )
}


@Composable
fun GifImage(url: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.dns_logo),
        contentDescription = null,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewScreenContent() {
    LikeDialog(
        onDismiss = { },
    )
}

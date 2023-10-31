package com.example.statisticsoflikes.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.statisticsoflikes.domain.DisLike
import com.example.statisticsoflikes.domain.Like
import com.example.statisticsoflikes.navigation.NavRout
import com.example.statisticsoflikes.presentation.MainViewModel


@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun ListPage(viewModel: MainViewModel, navController: NavHostController) {

    val like = viewModel.likeState.value
    val disLike = viewModel.disLikeState.value

    val statisticByDay = viewModel.getStatisticFromPreferences()
    val review = viewModel.getPreviewFromPreferences()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { navController.navigate(route = NavRout.LikesScreen.route) }
        ) { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back") }

        LazyColumn {
            item {
                StatisticsForCurrentDay(like, disLike)
            }
        }
        LazyColumn {
            items(statisticByDay) {
                ShowStatisticByDay(it)
            }
        }

        LazyColumn {
            items(review) { review ->
                ShowReview(review)
            }
        }
    }
}

@Composable
fun StatisticsForCurrentDay(like: Like, disLike: DisLike) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "${like.date} / Количество лайков: ${like.count} / Количество дизлайков: ${disLike.count}",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun ShowReview(item: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = item)
        }
    }
}

@Composable
fun ShowStatisticByDay(statisticByDay: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = statisticByDay)
        }
    }
}


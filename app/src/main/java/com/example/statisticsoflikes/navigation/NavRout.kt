package com.example.statisticsoflikes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.statisticsoflikes.presentation.MainViewModel
import com.example.statisticsoflikes.presentation.screen.LikesScreen
import com.example.statisticsoflikes.presentation.screen.ListPage

sealed class NavRout(val route: String) {
    object LikesScreen : NavRout("likes")
    object ListPage : NavRout("list")
}

@Composable
fun NotesNavHost(
    mViewModel: MainViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavRout.LikesScreen.route
    ) {

        composable(NavRout.LikesScreen.route) {
            LikesScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRout.ListPage.route) {
            ListPage(
                navController = navController,
                viewModel = mViewModel
            )
        }
    }
}


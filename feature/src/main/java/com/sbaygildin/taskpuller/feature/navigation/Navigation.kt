package com.sbaygildin.taskpuller.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import com.sbaygildin.taskpuller.feature.inbox.AdaptiveInboxNotesScreen
import com.sbaygildin.taskpuller.feature.timer.ui.TimerScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {

    val startPage = "timer"

    NavHost(navController, startDestination = startPage) {
        composable("timer") {
            TimerScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}

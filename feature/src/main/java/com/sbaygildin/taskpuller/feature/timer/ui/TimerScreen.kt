package com.sbaygildin.taskpuller.feature.timer.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sbaygildin.taskpuller.core.util.formatTime
import com.sbaygildin.taskpuller.core.R
import com.sbaygildin.taskpuller.feature.timer.TimerViewModel
import com.sbaygildin.taskpuller.feature.timer.service.TimerService

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel(),
) {
    val state by viewModel.timerState.collectAsState()
    var inputMinutes by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.checkSavedTimer(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputMinutes,
            onValueChange = { inputMinutes = it.filter { c -> c.isDigit() } },
            label = { Text(stringResource(R.string.txt_time_in_minutes)) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formatTime(state.remainingTime),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp))
        {
            Button(onClick = {
                val duration = inputMinutes.toLongOrNull()
                if (duration != null && duration > 0) {
                    viewModel.startTimer(duration)
                    val intent = Intent(context, TimerService::class.java).apply {
                        putExtra(TimerService.EXTRA_DURATION, duration * 60 * 1000L)
                    }
                    context.startForegroundService(intent)
                }
            })
            {
                Text(stringResource(R.string.txt_start))
            }

            Button(onClick = {
                viewModel.stopTimer()
                context.stopService(Intent(context, TimerService::class.java))
            }) {
                Text(stringResource(R.string.txt_stop))
            }

            Button(onClick = {
                viewModel.resetTimer()
                context.stopService(Intent(context, TimerService::class.java))
            }) {
                Text(stringResource(R.string.txt_reset))
            }
        }
    }
}

@Preview
@Composable
fun TimerScreenPreview() {
    TimerScreen()
}


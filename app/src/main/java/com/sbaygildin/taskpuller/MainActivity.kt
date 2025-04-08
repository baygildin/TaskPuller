package com.sbaygildin.taskpuller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sbaygildin.taskpuller.core.ui.theme.TaskPullerTheme
import com.sbaygildin.taskpuller.feature.navigation.AppNavigation
import com.sbaygildin.taskpuller.feature.timer.service.TimerService.Companion.CHANNEL_ID
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.app.ActivityCompat
import com.sbaygildin.taskpuller.core.R
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.provider.Settings

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
        }
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.txt_timer),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableVibration(true)
            enableLights(true)
            setSound(Settings.System.DEFAULT_NOTIFICATION_URI, null)
        }

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        enableEdgeToEdge()
        setContent {
            TaskPullerTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation (
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



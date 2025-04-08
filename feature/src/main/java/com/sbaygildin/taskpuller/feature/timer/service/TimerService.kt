package com.sbaygildin.taskpuller.feature.timer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import com.sbaygildin.taskpuller.data.datastore.TimerPreferences
import com.sbaygildin.taskpuller.core.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class TimerService() : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var durationMillis: Long = 0L
    private var startTimeMillis: Long = 0L
    private var timerJob: Job? = null

    companion object {
        const val CHANNEL_ID = "timer_channel"
        const val NOTIFICATION_ID = 1
        const val EXTRA_DURATION = "duration_in_millis"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        durationMillis = intent?.getLongExtra(EXTRA_DURATION, 0L) ?: 0L
        startTimeMillis = System.currentTimeMillis()
        TimerPreferences.saveTimer(applicationContext, startTimeMillis, durationMillis)
        startForeground(NOTIFICATION_ID, buildNotification(durationMillis))

        timerJob = serviceScope.launch {
            var lastMinuteReported = -1L
            while (true) {
                val elapsed = System.currentTimeMillis() - startTimeMillis
                val remaining = durationMillis - elapsed

                if (remaining <= 0L) {
                    sendFinishNotification()
                    stopSelf()
                    break
                }

                val currentMinute = TimeUnit.MILLISECONDS.toMinutes(remaining)
                if (currentMinute != lastMinuteReported) {
                    lastMinuteReported = currentMinute
                    updateNotification(remaining)
                }
                delay(500L)
            }
        }
        return START_STICKY
    }

    private fun buildNotification(remaining: Long): Notification {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(remaining)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.txt_timer_started))
            .setContentText(getString(R.string.txt_remains_minutes, minutes.toString()))
            .setWhen(System.currentTimeMillis())
            .setOnlyAlertOnce(false)
            .setSmallIcon(R.drawable.baseline_alarm_24)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification(remaining: Long) {
        val notification = buildNotification(remaining)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.txt_timer),
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    private fun sendFinishNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.txt_timer_expired))
            .setContentText(getString(R.string.txt_time_is_up))
            .setSmallIcon(R.drawable.baseline_alarm_24)
            .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
            .setAutoCancel(true)
            .build()

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(2, notification)

        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val canVibrate: Boolean = vibrator.hasVibrator()
        val milliseconds = 1000L

        @Suppress("MissingPermission")
        if (canVibrate) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    milliseconds,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerJob?.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}


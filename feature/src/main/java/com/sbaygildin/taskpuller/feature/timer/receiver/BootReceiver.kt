package com.sbaygildin.taskpuller.feature.timer.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.sbaygildin.taskpuller.data.datastore.TimerPreferences
import com.sbaygildin.taskpuller.core.R
import com.sbaygildin.taskpuller.feature.timer.service.TimerService
import java.util.concurrent.TimeUnit


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED){
            val (start, duration) = context?.let { TimerPreferences.getTimer(it) } ?: return
            val elapsed = System.currentTimeMillis() - start
            val remaining = duration - elapsed
            if (remaining > 0 ) {
                val serviceIntent = Intent(context, TimerService::class.java).apply{
                    putExtra(TimerService.EXTRA_DURATION, remaining)
                }
                context.startForegroundService(serviceIntent)
                val notification  = NotificationCompat.Builder(context, TimerService.CHANNEL_ID)
                    .setContentTitle(context.getString(R.string.timer_restored))
                    .setContentText("Осталось ${TimeUnit.MILLISECONDS.toMinutes(remaining)} мин.")
                    .setSmallIcon(R.drawable.baseline_alarm_24)
                    .build()
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(3, notification)
            } else {
                TimerPreferences.clear(context)
            }
        }
    }
}


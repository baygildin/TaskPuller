package com.sbaygildin.taskpuller.data.datastore
import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


val Context.dataStore by preferencesDataStore("timer_prefs")

object TimerPreferences {
    val KEY_START_TIME = longPreferencesKey("start_time")
    val KEY_DURATION = longPreferencesKey("duration")

    fun saveTimer(context: Context, startTime: Long, duration: Long){
        runBlocking{
            context.dataStore.edit {
                it[KEY_START_TIME] = startTime
                it[KEY_DURATION] = duration
            }
        }
    }

    fun getTimer(context: Context): Pair<Long, Long>? {
        return runBlocking {
            val prefs = context.dataStore.data.first()
            val start = prefs[KEY_START_TIME] ?: return@runBlocking null
            val duration = prefs[KEY_DURATION] ?: return@runBlocking null
            return@runBlocking start to duration
        }
    }
    fun clear(context: Context) {
        runBlocking {
            context.dataStore.edit { it.clear() }
        }
    }

}







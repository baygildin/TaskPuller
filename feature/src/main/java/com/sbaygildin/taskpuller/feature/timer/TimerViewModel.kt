package com.sbaygildin.taskpuller.feature.timer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbaygildin.taskpuller.data.datastore.TimerPreferences
import com.sbaygildin.taskpuller.domain.model.TimerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel() {

    private val _timerState = MutableStateFlow(TimerState())
    val timerState: StateFlow<TimerState> = _timerState

    private var timerJob: Job? = null
    private var totalTime: Long = 0L


    fun checkSavedTimer(context: Context) {
        viewModelScope.launch {
            val saved = TimerPreferences.getTimer(context)
            if (saved != null) {
                val (start, duration) = saved
                val elapsed = System.currentTimeMillis() - start
                val remaining = duration - elapsed
                if (remaining > 0) {
                    _timerState.value = TimerState(
                        remainingTime = remaining,
                        isRunning = true
                    )
                    startTimerInternal(remaining)
                } else {
                    TimerPreferences.clear(context)
                }
            }
        }

    }

    fun startTimer(minutes: Long) {
        if (_timerState.value.isRunning) return

        totalTime = minutes * 60 * 1000L
        val startTime = System.currentTimeMillis()
        val endTime = startTime + totalTime

        _timerState.value = TimerState(remainingTime = totalTime, isRunning = true)

        timerJob = viewModelScope.launch {
            while (true) {
                val now = System.currentTimeMillis()
                val remaining = endTime - now

                if (remaining <= 0L) {
                    _timerState.value = TimerState(remainingTime = 0L, isRunning = false)
                    break
                }

                _timerState.value = _timerState.value.copy(remainingTime = remaining)
                delay(1000L)
            }
        }
    }

    private fun startTimerInternal(initial: Long) {
        timerJob?.cancel()
        val startTime = System.currentTimeMillis()
        val endTime = startTime + initial

        timerJob = viewModelScope.launch {
            while (true) {
                val now = System.currentTimeMillis()
                val remaining = endTime - now

                if (remaining <= 0L) {
                    _timerState.value = TimerState(remainingTime = 0L, isRunning = false)
                    break
                }

                _timerState.value = _timerState.value.copy(remainingTime = remaining)
                delay(1000L)
            }
        }
    }

    fun stopTimer() {

        timerJob?.cancel()
        _timerState.value = _timerState.value.copy(isRunning = false)
    }

    fun resetTimer() {
        stopTimer()
        _timerState.value = TimerState()
    }
}


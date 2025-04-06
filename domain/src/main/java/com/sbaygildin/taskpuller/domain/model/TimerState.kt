package com.sbaygildin.taskpuller.domain.model

data class TimerState(
    val startTime: Long= 0L,
    val remainingTime: Long = 0L,
    val isRunning: Boolean = false
)

package com.sbaygildin.taskpuller.domain.model

data class Note(
    val id: Int = 0,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)

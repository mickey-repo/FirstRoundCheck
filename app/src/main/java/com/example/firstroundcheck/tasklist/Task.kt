package com.example.firstroundcheck.tasklist

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val isComplete: Boolean = false
)
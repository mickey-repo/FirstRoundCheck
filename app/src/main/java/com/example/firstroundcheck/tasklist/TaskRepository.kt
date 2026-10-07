package com.example.firstroundcheck.tasklist

import kotlinx.coroutines.delay

class TaskRepository {
    suspend fun getTaskList(): List<Task> {
        delay(10000)
        return listOf(
            Task(
                title = "task1"
            ),
            Task(
                title = "task2"
            )
        )
    }
}
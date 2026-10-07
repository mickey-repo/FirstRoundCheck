package com.example.firstroundcheck.tasklist

data class TaskListUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val errorMessage:String? = null
)

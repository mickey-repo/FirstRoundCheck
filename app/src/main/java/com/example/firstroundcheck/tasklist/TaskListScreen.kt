package com.example.firstroundcheck.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel
){
    val uiState by viewModel.taskListUiState.collectAsStateWithLifecycle()
    val errorMessage = uiState.errorMessage
    when{
        uiState.isLoading -> { Text(text = "loading") }
        errorMessage != null ->{
            Column {
                Text(text = errorMessage)
                Button(onClick = {viewModel.onRetry()}) {
                    Text(text = "Retry")
                }
            }
        }
        uiState.tasks.isEmpty() -> {Text(text = "no tasks")}
        else -> {
            LazyColumn {
                items(
                    items = uiState.tasks,
                    key = {task -> task.id}
                ) { task->
                    Text(text = task.title)
                }
            }
        }
    }
}
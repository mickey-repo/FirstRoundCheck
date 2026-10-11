package com.example.firstroundcheck.tasklist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _taskListUiState = MutableStateFlow(TaskListUiState())
    val taskListUiState : StateFlow<TaskListUiState> = _taskListUiState.asStateFlow()
    private var loadCount = 0

    init {
        Log.d("TaskListViewModel", "ViewModel created: ${hashCode()}")
        loadTasks()
    }

    fun onRetry(){
        loadTasks()
    }

    private fun loadTasks(){
        loadCount++
        Log.d(
            "TaskListViewModel",
            "loadTasks called, count=$loadCount, viewModel=${hashCode()}"
        )
        viewModelScope.launch {
            _taskListUiState.update { currentState ->
                currentState.copy(isLoading = true, tasks = emptyList(), errorMessage=null)
            }
            try{
                val tasks = taskRepository.getTaskList()
                _taskListUiState.update { currentState ->
                    currentState.copy(isLoading = false, tasks = tasks, errorMessage=null)
                }
            } catch (e: CancellationException){
                throw e
            } catch (e: Exception){
                _taskListUiState.update { currentState ->
                    currentState.copy(isLoading = false, tasks = emptyList(), errorMessage = "failed")
                }
            }
        }
    }

    override fun onCleared() {
        Log.d("TaskListViewModel", "ViewModel cleared: ${hashCode()}")
        super.onCleared()
    }
}
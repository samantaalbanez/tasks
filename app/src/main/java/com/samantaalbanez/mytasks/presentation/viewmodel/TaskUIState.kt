package com.samantaalbanez.mytasks.presentation.viewmodel

import com.samantaalbanez.mytasks.data.model.Task

internal sealed class TaskUiState {
    data class Success(val tasks: List<Task>) : TaskUiState()
    data class Error(val message: String) : TaskUiState()
    object Empty : TaskUiState()
}

package com.samantaalbanez.mytasks.presentation.viewmodel

import com.samantaalbanez.mytasks.data.model.Task

internal sealed class TaskUiEvent {
    data object Idle : TaskUiEvent()
    data class AddTask(val title: String) : TaskUiEvent()
    data class CompleteTask(val task: Task) : TaskUiEvent()
    data class RemoveTask(val task: Task) : TaskUiEvent()
}

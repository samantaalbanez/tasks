package com.samantaalbanez.mytasks.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.domain.usecase.AddTaskUseCase
import com.samantaalbanez.mytasks.domain.usecase.CompleteTaskUseCase
import com.samantaalbanez.mytasks.domain.usecase.DeleteTaskUseCase
import com.samantaalbanez.mytasks.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TaskViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val completeTaskUseCase: CompleteTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    var text = mutableStateOf("")
        private set

    val tasks = getTasksUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun onEvent(event: TaskUiEvent) {
        when (event) {
            is TaskUiEvent.AddTask -> addTask(event.title)
            is TaskUiEvent.CompleteTask -> completeTask(event.task)
            is TaskUiEvent.RemoveTask -> deleteTask(event.task)
            is TaskUiEvent.Idle -> Unit
        }
    }

    fun updateText(textValue: String) {
        text.value = textValue
    }

    private fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(task)
        }
    }

    private fun completeTask(task: Task) {
        viewModelScope.launch {
            completeTaskUseCase.invoke(task)
        }
    }

    private fun addTask(title: String) {
        viewModelScope.launch {
            addTaskUseCase(title)
        }
    }
}

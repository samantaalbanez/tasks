package com.samantaalbanez.mytasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samantaalbanez.mytasks.domain.usecase.AddTaskUseCase
import com.samantaalbanez.mytasks.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TaskViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    val tasks = getTasksUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addTask(title: String) {
        viewModelScope.launch {
            addTaskUseCase(title)
        }
    }
}

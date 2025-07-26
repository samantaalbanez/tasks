package com.samantaalbanez.mytasks.domain.usecase

import com.samantaalbanez.mytasks.data.repository.TaskRepository
import javax.inject.Inject

internal class AddTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(title: String) {
        repository.addTask(title)
    }
}

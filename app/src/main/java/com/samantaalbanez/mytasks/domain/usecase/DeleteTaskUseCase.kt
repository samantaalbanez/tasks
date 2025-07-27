package com.samantaalbanez.mytasks.domain.usecase

import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.data.repository.TaskRepository
import javax.inject.Inject

internal class DeleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {

    suspend operator fun invoke(task: Task) {
        repository.removeTask(task)
    }
}

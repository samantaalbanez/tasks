package com.samantaalbanez.mytasks.domain.usecase

import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> = repository.tasks
}

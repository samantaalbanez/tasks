package com.samantaalbanez.mytasks.data.repository

import com.samantaalbanez.mytasks.data.dao.TaskDao
import com.samantaalbanez.mytasks.data.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(title: String) {
        taskDao.insertTask(Task(title = title))
    }

    suspend fun updateTaskToCompleted(task: Task) {
        taskDao.updateTask(task.copy(completed = !task.completed))
    }

    suspend fun removeTask(task: Task) {
        taskDao.deleteTask(task)
    }
}

package com.samantaalbanez.mytasks.data.dao

import com.samantaalbanez.mytasks.data.model.Task
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class TaskDaoTest {

    @RelaxedMockK
    lateinit var taskDao: TaskDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
    }

    @Test
    fun `when insertTask should call dao insert`() = runTest {
        // Given
        val task = Task(title = "Task1")
        coEvery { taskDao.insertTask(any()) } just Runs

        // When
        taskDao.insertTask(task)

        // Then
        coVerify { taskDao.insertTask(task) }
    }

    @Test
    fun `when updateTask should call dao update`() = runTest {
        // Given
        val task = Task(title = "Task1")
        coEvery { taskDao.updateTask(any()) } just Runs

        // When
        taskDao.updateTask(task)

        // Then
        coVerify { taskDao.updateTask(task) }
    }

    @Test
    fun `when deleteTask should call dao delete`() = runTest {
        // Given
        val task = Task(title = "Task1")
        coEvery { taskDao.deleteTask(any()) } just Runs

        // When
        taskDao.deleteTask(task)

        // Then
        coVerify { taskDao.deleteTask(task) }
    }

    @Test
    fun `when getAllTasks should return flow of tasks`() = runTest {
        // Given
        val tasks = listOf(Task(title = "Task1"), Task(title = "Task2"), Task(title = "Task3"))
        every { taskDao.getAllTasks() } returns flowOf(tasks)

        // When
        val result = taskDao.getAllTasks()

        // Then
        assertEquals(tasks, result.firstOrNull())
    }
}

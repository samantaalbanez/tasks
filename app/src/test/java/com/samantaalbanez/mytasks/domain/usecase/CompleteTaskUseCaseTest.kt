package com.samantaalbanez.mytasks.domain.usecase

import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.data.repository.TaskRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class CompleteTaskUseCaseTest {

    @RelaxedMockK
    lateinit var repository: TaskRepository

    private lateinit var useCase: CompleteTaskUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
        useCase = CompleteTaskUseCase(repository)
    }

    @Test
    fun `when invoke use case with a task completed then update the status to no completed`() = runTest {
        // Given
        val task = Task(title = "task 01", completed = false)
        coEvery { repository.updateTask(any()) } returns Unit

        // When
        useCase.invoke(task)

        // Then
        coVerify { repository.updateTask(task.copy(completed = true)) }
    }

    @Test
    fun `when invoke use case with a task no completed then update the status to completed`() = runTest {
        // Given
        val task = Task(title = "task 01", completed = true)
        coEvery { repository.updateTask(any()) } returns Unit

        // When
        useCase.invoke(task)

        // Then
        coVerify { repository.updateTask(task.copy(completed = false)) }
    }
}

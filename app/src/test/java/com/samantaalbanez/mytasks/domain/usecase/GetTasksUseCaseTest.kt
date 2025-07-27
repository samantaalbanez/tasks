package com.samantaalbanez.mytasks.domain.usecase

import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.data.repository.TaskRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetTasksUseCaseTest {

    @RelaxedMockK
    lateinit var repository: TaskRepository

    lateinit var useCase: GetTasksUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
        useCase = GetTasksUseCase(repository)
    }

    @Test
    fun `when invoke use case then return list of tasks`() = runTest {
        // Given
        val tasks = listOf(Task(title = "task1"), Task(title = "task2"), Task(title = "task3"))
        every { repository.tasks } returns flowOf(tasks)

        // When
        val result = useCase.invoke()

        // Then
        assertEquals(tasks, result.firstOrNull())
    }
}

package com.samantaalbanez.mytasks.domain.usecase

import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.data.repository.TaskRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class DeleteTaskUseCaseTest {

    @RelaxedMockK
    lateinit var repository: TaskRepository

    lateinit var useCase: DeleteTaskUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
        useCase = DeleteTaskUseCase(repository)
    }

    @Test
    fun `when invoke use case with a task then remove it`() = runTest {
        // Given
        val task = Task(title = "Shopping")
        coEvery { repository.removeTask(any()) } returns Unit

        // When
        useCase.invoke(task)

        // Then
        coVerify { repository.removeTask(task) }
    }
}

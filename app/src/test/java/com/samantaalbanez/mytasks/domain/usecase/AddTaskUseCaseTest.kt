package com.samantaalbanez.mytasks.domain.usecase

import com.samantaalbanez.mytasks.data.repository.TaskRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class AddTaskUseCaseTest {

    @RelaxedMockK
    lateinit var repository: TaskRepository

    private lateinit var useCase: AddTaskUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
        useCase = AddTaskUseCase(repository)
    }

    @Test
    fun `when invoke use case with task title then add task should be called`() = runTest {
        // Given
        val title = "study english"
        coEvery { repository.addTask(any()) } returns Unit

        // When
        useCase.invoke(title)

        // Then
        coVerify { repository.addTask(title) }
    }
}

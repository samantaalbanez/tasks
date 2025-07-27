package com.samantaalbanez.mytasks.presentation.viewmodel

import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.domain.usecase.AddTaskUseCase
import com.samantaalbanez.mytasks.domain.usecase.CompleteTaskUseCase
import com.samantaalbanez.mytasks.domain.usecase.DeleteTaskUseCase
import com.samantaalbanez.mytasks.domain.usecase.GetTasksUseCase
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TaskViewModelTest {

    @RelaxedMockK
    lateinit var getTasksUseCase: GetTasksUseCase

    @RelaxedMockK
    lateinit var addTaskUseCase: AddTaskUseCase

    @RelaxedMockK
    lateinit var completeTaskUseCase: CompleteTaskUseCase

    @RelaxedMockK
    lateinit var deleteTaskUseCase: DeleteTaskUseCase

    private lateinit var viewModel: TaskViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = TaskViewModel(
            getTasksUseCase = getTasksUseCase,
            addTaskUseCase = addTaskUseCase,
            completeTaskUseCase = completeTaskUseCase,
            deleteTaskUseCase = deleteTaskUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `when given tasks then return a list of tasks`() = runTest {
        // Given
        val listOfTasks = listOf(
            Task(title = "task1"),
            Task(title = "task2"),
            Task(title = "task3"),
            Task(title = "task4")
        )
        every { getTasksUseCase.invoke() } returns flowOf(listOfTasks)

        // When
        advanceUntilIdle()
        viewModel.tasks.first()

        // Then
        coVerify(exactly = 1) { getTasksUseCase() }
    }

    @Test
    fun `when invoke the event AddTask then add a new task`() = runTest {
        // Given
        val title = "my task"

        // When
        viewModel.onEvent(TaskUiEvent.AddTask(title))
        advanceUntilIdle()

        // Then
        coVerify(exactly = 1) { addTaskUseCase(title) }
    }

    @Test
    fun `when invoke the event delete task then add a new task`() = runTest {
        // Given
        val task = Task(title = "task1")

        // When
        viewModel.onEvent(TaskUiEvent.RemoveTask(task))
        advanceUntilIdle()

        // Then
        coVerify(exactly = 1) { deleteTaskUseCase(task) }
    }

    @Test
    fun `when invoke the event complete task then add a new task`() = runTest {
        // Given
        val task = Task(title = "task1", completed = false)

        // When
        viewModel.onEvent(TaskUiEvent.CompleteTask(task))
        advanceUntilIdle()

        // Then
        coVerify(exactly = 1) { completeTaskUseCase(task) }
    }
}

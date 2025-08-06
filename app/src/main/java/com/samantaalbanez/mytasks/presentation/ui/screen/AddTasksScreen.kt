package com.samantaalbanez.mytasks.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.samantaalbanez.mytasks.R
import com.samantaalbanez.mytasks.presentation.ui.components.CardTask
import com.samantaalbanez.mytasks.presentation.ui.components.CircleIcon
import com.samantaalbanez.mytasks.presentation.ui.components.TaskList
import com.samantaalbanez.mytasks.presentation.ui.components.TextInput
import com.samantaalbanez.mytasks.presentation.ui.components.Title
import com.samantaalbanez.mytasks.presentation.viewmodel.TaskUiEvent
import com.samantaalbanez.mytasks.presentation.viewmodel.TaskViewModel
import com.samantaalbanez.mytasks.ui.theme.BackGroundColor
import com.samantaalbanez.mytasks.ui.theme.CardConcludedColor
import com.samantaalbanez.mytasks.ui.theme.CardPendingColor

@Composable
internal fun AddTaskScreen(
    viewModel: TaskViewModel
) {
    val tasks by viewModel.tasks.collectAsState()
    val text by remember { viewModel.text }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ) {
        CircleIcon()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title(stringResource(R.string.title))

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                CardTask(
                    label = stringResource(R.string.pending),
                    count = tasks.filter { !it.completed }.size,
                    color = CardConcludedColor
                )
                CardTask(
                    label = stringResource(R.string.concluded),
                    count = tasks.filter { it.completed }.size,
                    color = CardPendingColor
                )
            }

            Spacer(Modifier.height(30.dp))

            TextInput(
                text = text,
                onTextChange = { viewModel.updateText(it) },
                onAddClick = {
                    viewModel.onEvent(TaskUiEvent.AddTask(title = text))
                    viewModel.updateText(textValue = "")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            Spacer(Modifier.height(10.dp))

            TaskList(
                tasks = tasks,
                onCheckedChange = { task ->
                    viewModel.onEvent(TaskUiEvent.CompleteTask(task))
                },
                onSwipeToRemove = { task ->
                    viewModel.onEvent(TaskUiEvent.RemoveTask(task))
                }
            )
        }
    }
}

package com.samantaalbanez.mytasks.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.ui.theme.PrimaryColor

@Composable
internal fun TaskList(
    tasks: List<Task>,
    onCheckedChange: (Task) -> Unit,
    onSwipeToRemove: (Task) -> Unit
) {
    Column{
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(tasks, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onCheckedChange = onCheckedChange,
                    onSwipeToRemove = onSwipeToRemove
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskItem(
    task: Task,
    onCheckedChange: (Task) -> Unit,
    onSwipeToRemove: (Task) -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            onSwipeToRemove.invoke(task)
            true
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier.fillMaxWidth(),
        backgroundContent = {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onCheckedChange.invoke(task)
            }
        ) {
            Row(
                modifier = Modifier.padding(16.dp).background(Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = task.completed,
                    onCheckedChange = {
                        onCheckedChange.invoke(task)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PrimaryColor,
                        uncheckedColor = Color.White
                    )
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = if (task.completed) TextDecoration.LineThrough else null
                        )
                    )
                }
            }
        }
    }
}

package com.samantaalbanez.mytasks.presentation.ui.components

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.samantaalbanez.mytasks.data.model.Task
import com.samantaalbanez.mytasks.ui.theme.PrimaryColor

@Composable
internal fun TaskList(tasks: List<Task>) {
    Column{
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(tasks) { task ->
                TaskItem(task)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
internal fun TaskItem(task: Task) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp).background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = null,
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

package com.samantaalbanez.mytasks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.samantaalbanez.mytasks.presentation.ui.screen.AddTaskScreen
import com.samantaalbanez.mytasks.presentation.viewmodel.TaskViewModel
import com.samantaalbanez.mytasks.ui.theme.TasksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasksTheme {
                AddTaskScreen(viewModel)
            }
        }
    }
}

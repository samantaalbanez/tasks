package com.samantaalbanez.mytasks.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
internal data class Task(
    @PrimaryKey val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val completed: Boolean = false
)

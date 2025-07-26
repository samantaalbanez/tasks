package com.samantaalbanez.mytasks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samantaalbanez.mytasks.data.dao.TaskDao
import com.samantaalbanez.mytasks.data.model.Task

@Database(entities = [Task::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

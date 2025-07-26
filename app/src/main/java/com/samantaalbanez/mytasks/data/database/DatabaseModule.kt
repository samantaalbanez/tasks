package com.samantaalbanez.mytasks.data.database

import android.content.Context
import androidx.room.Room
import com.samantaalbanez.mytasks.data.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "task-app-db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()
}

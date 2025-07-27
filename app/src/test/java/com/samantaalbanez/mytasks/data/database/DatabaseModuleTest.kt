package com.samantaalbanez.mytasks.data.database

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

internal class DatabaseModuleTest {

    @RelaxedMockK
    lateinit var context: Context

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        clearAllMocks()
    }

    @Test
    fun `when provideDatabase should return AppDatabase`() {
        // When
        val db = DatabaseModule.provideDatabase(context)

        // Then
        assertNotNull(db)
    }

    @Test
    fun `when provideTaskDao should return TaskDao`() {
        // When
        val db = DatabaseModule.provideDatabase(context)
        val dao = DatabaseModule.provideTaskDao(db)

        // Then
        assertNotNull(dao)
    }
}

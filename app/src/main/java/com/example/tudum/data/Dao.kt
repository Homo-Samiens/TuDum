package com.example.tudum.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?


    @Query("SELECT * FROM Task")
    fun getTask(): Flow<List<Task>>

}
package com.example.tudum.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface Repo {

        suspend fun upsertTask(task: Task)

        suspend fun deleteTask(task: Task)

        suspend fun getTaskById(id: Int): Task?

        fun getTask(): Flow<List<Task>>

}
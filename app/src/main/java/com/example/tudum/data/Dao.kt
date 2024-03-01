package com.example.tudum.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.tudum.Todo
import kotlinx.coroutines.flow.Flow

//DAO

@Dao
interface Dao {

    @Upsert
    suspend fun upsertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM Todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Query("SELECT * FROM Todo")
    fun getTodo(): Flow<List<com.example.tudum.data.Todo>>

}
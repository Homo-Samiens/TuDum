package com.example.tudum.data

import kotlinx.coroutines.flow.Flow

interface Repo {

        suspend fun upsertTodo(todo: Todo)

        suspend fun deleteTodo(todo: Todo)

        suspend fun getTodoById(id: Int): Todo?

        fun getTodo(): Flow<List<Todo>>

}
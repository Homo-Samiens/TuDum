package com.example.tudum.data

import com.example.tudum.Todo
import kotlinx.coroutines.flow.Flow

class RepoImpl(
    private val dao: Dao
): Repo {
    override suspend fun upsertTodo(todo: Todo) {
        dao.upsertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }


    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodo(): Flow<List<com.example.tudum.data.Todo>> {
        return dao.getTodo()
    }
}
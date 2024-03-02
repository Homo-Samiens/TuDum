package com.example.tudum.data

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

    override fun getTodo(): Flow<List<Todo>> {
        return dao.getTodo()
    }
}
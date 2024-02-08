package com.example.tudum.data

import kotlinx.coroutines.flow.Flow

class RepoImpl(
    private val dao: Dao
): Repo {
    override suspend fun upsertTask(task: Task) {
        dao.upsertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override fun getTask(): Flow<List<Task>> {
        return dao.getTask()
    }
}
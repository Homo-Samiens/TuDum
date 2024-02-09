package com.example.tudum.ui.theme.todo_list

import com.example.tudum.data.Task

sealed class TodoListEvent {

    data class DeleteTask(val task: Task): TodoListEvent()
    data class OnDoneChange(val task: Task, val isDone: Boolean): TodoListEvent()
    object onUndoDeleteClick: TodoListEvent()
    data class onTodoClick(val task: Task): TodoListEvent()
    object OnAddTaskClick: TodoListEvent()

}
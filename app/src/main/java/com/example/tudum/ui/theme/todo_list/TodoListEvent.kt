package com.example.tudum.ui.theme.todo_list

import com.example.tudum.data.Todo

sealed class TodoListEvent {

    data class OnTodoClick(val todo: Todo): TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListEvent()
    data class OnDeleteTodoClick(val todo: Todo): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent()

}
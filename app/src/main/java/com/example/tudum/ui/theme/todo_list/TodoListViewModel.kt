package com.example.tudum.ui.theme.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tudum.data.Repo
import com.example.tudum.data.Todo
import com.example.tudum.util.Routes
import com.example.tudum.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: Repo
) : ViewModel() {

    val todos = repository.getTodo()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deleteTodo: Todo? = null

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun onEvent(event: TodoListEvent) {
        when (event) {

            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }

            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }

            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.upsertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deleteTodo = event.todo
                    repository.deleteTodo(event.todo)
//                    sendUiEvent(
//                        UiEvent.ShowSnackbar(
//                            message = "Todo Deleted",
//                            action = "Undo"
//                        )
//                    )
                }
            }

            is TodoListEvent.OnUndoDeleteClick -> {
                deleteTodo.let { todo ->
                    viewModelScope.launch {
                        if (todo != null) {
                            repository.upsertTodo(todo)
                        }
                    }
                }
            }

        }
    }

}
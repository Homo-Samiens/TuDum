package com.example.tudum.ui.theme.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tudum.Util.Routes
import com.example.tudum.Util.UiEvent
import com.example.tudum.data.Repo
import com.example.tudum.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: Repo
): ViewModel() {

    val todos = repository.getTask()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deleteTask: Task? = null

    fun onEvent(event: TodoListEvent){
        when(event){

            is TodoListEvent.onTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.task.id}"))
            }

            is TodoListEvent.OnAddTaskClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }

            is TodoListEvent.onUndoDeleteClick -> {
                deleteTask?.let { task ->
                    viewModelScope.launch {
                        repository.upsertTask(task)
                    }
                }
            }

            is TodoListEvent.DeleteTask -> {
                viewModelScope.launch {
                    repository.deleteTask(event.task)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Task Deleted",
                        action = "undo"
                    ))
                }
            }

            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.upsertTask(
                        event.task.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}
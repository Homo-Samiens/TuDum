package com.example.tudum.ui.theme.todo_list

import androidx.lifecycle.ViewModel
import com.example.tudum.Util.UiEvent
import com.example.tudum.data.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: Repo
): ViewModel() {

    val todos = repository.getTask()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

}
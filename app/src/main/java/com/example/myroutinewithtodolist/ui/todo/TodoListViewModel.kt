package com.example.myroutinewithtodolist.ui.todo

import androidx.lifecycle.ViewModel
import com.example.myroutinewithtodolist.model.TodoListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 *
 * @author jonghyuk.
 * @since 4/25/24.
 */


class TodoListViewModel : ViewModel() {

	private val _todoList = MutableStateFlow<List<TodoListItem>>(emptyList())
	val todoList = _todoList.asStateFlow()

	init {
		fetchTodoList()
	}

	private fun fetchTodoList() {
		val dummyTodoList = listOf(
			TodoListItem(0, "Apple"),
			TodoListItem(1, "Banana"),
			TodoListItem(2, "Carrot"),
			TodoListItem(3, "Grape")
		)

		_todoList.value = dummyTodoList
	}

	fun updateCheckState(id: Int, checked: Boolean) {
		val newList = _todoList.value.map {
			if (it.id == id)
				it.copy(isChecked = checked)
			else
				it
		}

		_todoList.value = newList
	}
}
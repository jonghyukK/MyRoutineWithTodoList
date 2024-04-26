package com.example.myroutinewithtodolist.ui.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 *
 * @author jonghyuk.
 * @since 4/26/24.
 */
class TodoItemAddViewModel : ViewModel() {

	private val _todoContents = MutableStateFlow("")
	val todoContents = _todoContents.asStateFlow()

	fun setTodoContents(contents: String) {
		_todoContents.value = contents
	}
}
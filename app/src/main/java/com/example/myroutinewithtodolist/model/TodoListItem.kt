package com.example.myroutinewithtodolist.model

/**
 *
 * @author jonghyuk.
 * @since 4/25/24.
 */
data class TodoListItem(
	val id: Int = 0,
	val contents: String = "",
	val isChecked: Boolean = false,
)
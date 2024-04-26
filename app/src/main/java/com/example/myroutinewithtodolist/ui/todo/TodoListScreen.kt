package com.example.myroutinewithtodolist.ui.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myroutinewithtodolist.model.TodoListItem
import com.example.myroutinewithtodolist.ui.common.CommonAppBar

/**
 *
 * @author jonghyuk.
 * @since 4/25/24.
 */

@Composable
fun TodoListScreen(
	viewModel: TodoListViewModel = viewModel(),
) {
	val todoList by viewModel.todoList.collectAsStateWithLifecycle()

	Scaffold(
		topBar = {
			CommonAppBar(
				showBackButton = false,
				title = "TodoList"
			)
		}
	) { innerPadding ->
		Column(
			modifier = Modifier.padding(innerPadding)
		) {
			TodoListView(
				todoList = todoList,
				onChangeChecked = { id, isChecked -> viewModel.updateCheckState(id, isChecked) }
			)
		}
	}
}

@Composable
fun TodoListView(
	modifier: Modifier = Modifier,
	todoList: List<TodoListItem>,
	onChangeChecked: (Int, Boolean) -> Unit,
) {
	LazyColumn(modifier.padding(vertical = 10.dp)) {
		items(
			items = todoList,
			key = { item -> item.id }
		) { item ->
			TodoListItem(
				todoItem = item,
				onChangeChecked = onChangeChecked
			)
		}
	}
}


@Composable
fun TodoListItem(
	modifier: Modifier = Modifier,
	todoItem: TodoListItem,
	onChangeChecked: (Int, Boolean) -> Unit,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(46.dp)
			.clickable { onChangeChecked.invoke(todoItem.id, !todoItem.isChecked) }
			.padding(horizontal = 20.dp),
		verticalAlignment = Alignment.CenterVertically,
	) {
		Checkbox(
			checked = todoItem.isChecked,
			onCheckedChange = null
		)

		Spacer(modifier = Modifier.width(8.dp))

		Text(
			text = todoItem.contents,
			fontSize = 16.sp,
			fontWeight = FontWeight.Medium
		)
	}
}


@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview(
	modifier: Modifier = Modifier,
) {
	TodoListScreen()
}
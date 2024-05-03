package com.example.myroutinewithtodolist.ui.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 *
 * @author jonghyuk.
 * @since 4/26/24.
 */


@Composable
fun TodoItemAddScreen(
	modifier: Modifier = Modifier,
	popBackStack: () -> Unit,
	onClickAdd: (String) -> Unit,
	viewModel: TodoItemAddViewModel = viewModel(),
) {
	val todoContents by viewModel.todoContents.collectAsStateWithLifecycle()

	Box(modifier = modifier) {
		ContentsTextField(
			contents = todoContents,
			onValueChanged = { newValue -> viewModel.setTodoContents(newValue) }
		)

		AddBottomButton(modifier = Modifier.align(Alignment.BottomCenter)) {
			onClickAdd.invoke(todoContents)
			popBackStack.invoke()
		}
	}
}

@Composable
fun ContentsTextField(
	contents: String,
	onValueChanged: (String) -> Unit,
) {
	OutlinedTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 15.dp, horizontal = 20.dp),
		value = contents,
		onValueChange = { str -> onValueChanged(str) },
		label = { Text(text = "What should I do?") }
	)
}

@Composable
fun AddBottomButton(
	modifier: Modifier = Modifier,
	onClickAdd: () -> Unit,
) {
	Button(
		onClick = onClickAdd,
		modifier = modifier
			.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
			.height(50.dp)
			.fillMaxWidth(),
		shape = RoundedCornerShape(10)
	) {
		Text(
			text = "Add",
			fontSize = 15.sp,
			fontWeight = FontWeight.Bold
		)
	}
}
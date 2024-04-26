package com.example.myroutinewithtodolist.ui.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myroutinewithtodolist.ui.common.CommonAppBar

/**
 *
 * @author jonghyuk.
 * @since 4/26/24.
 */


@Composable
fun TodoItemAddScreen(
	viewModel: TodoItemAddViewModel = viewModel(),
) {
	val todoContents by viewModel.todoContents.collectAsStateWithLifecycle()
	val activity = LocalContext.current as? ComponentActivity

	Log.e("abc123", "AddScreen !")

	Scaffold(
		topBar = {
			CommonAppBar(
				showBackButton = true,
				title = "Add Todo Item"
			)
		}
	) { innerPadding ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
		) {
			ContentsTextField(
				contents = todoContents,
				onValueChanged = { newValue -> viewModel.setTodoContents(newValue) }
			)

			AddBottomButton(modifier = Modifier.align(Alignment.BottomCenter)) {
				Intent().apply {
					putExtra("TODO_CONTENTS", todoContents)
					activity?.setResult(RESULT_OK, this)
				}
				activity?.finish()
			}
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
	Log.e("abc123", "Add Button !")

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

@Preview(showBackground = true)
@Composable
fun TodoItemAddScreenPreview() {
	TodoItemAddScreen()
}
package com.example.myroutinewithtodolist.ui.add

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myroutinewithtodolist.ui.theme.MyRoutineWithTodoListTheme

/**
 *
 * @author jonghyuk.
 * @since 4/26/24.
 */

class TodoItemAddActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyRoutineWithTodoListTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					TodoItemAddScreen()
				}
			}
		}
	}
}
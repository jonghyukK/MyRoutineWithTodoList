package com.example.myroutinewithtodolist

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myroutinewithtodolist.ui.add.TodoItemAddScreen
import com.example.myroutinewithtodolist.ui.todo.TodoListScreen
import com.example.myroutinewithtodolist.ui.todo.TodoListViewModel

/**
 *
 * @author jonghyuk.
 * @since 4/25/24.
 */

enum class TodoListScreen(@StringRes val title: Int) {
	HOME(title = R.string.screen_title_home),
	ADD(title = R.string.screen_title_add)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListAppBar(
	currentScreen: TodoListScreen,
	canNavigationBack: Boolean,
	navigateUp: () -> Unit,
	modifier: Modifier = Modifier,
) {
	TopAppBar(
		title = { Text(text = stringResource(currentScreen.title)) },
		colors = TopAppBarDefaults.mediumTopAppBarColors(
			containerColor = MaterialTheme.colorScheme.primaryContainer
		),
		modifier = modifier,
		navigationIcon = {
			if (canNavigationBack) {
				IconButton(onClick = navigateUp) {
					Icon(
						imageVector = Icons.AutoMirrored.Filled.ArrowBack,
						contentDescription = "Arrow Back"
					)
				}
			}
		}
	)
}

@Composable
fun TodoListApp(
	navController: NavHostController = rememberNavController(),
	viewModel: TodoListViewModel = viewModel(),
) {
	val backStackEntry by navController.currentBackStackEntryAsState()
	val currentScreen = TodoListScreen.valueOf(
		backStackEntry?.destination?.route ?: TodoListScreen.HOME.name
	)

	val todoItems by viewModel.todoList.collectAsStateWithLifecycle()

	Log.e("abc123", "TodoListApp Func...")

	Scaffold(
		topBar = {
			TodoListAppBar(
				currentScreen = currentScreen,
				canNavigationBack = navController.previousBackStackEntry != null,
				navigateUp = { navController.navigateUp() }
			)
		},
		floatingActionButton = {
			if (currentScreen == TodoListScreen.HOME) {
				FloatingActionButton(
					onClick = { navController.navigate(TodoListScreen.ADD.name) }
				) {
					Icon(Icons.Default.Add, contentDescription = "Add")
				}
			}
		}
	) { innerPadding ->

		NavHost(
			navController = navController,
			startDestination = TodoListScreen.HOME.name,
			modifier = Modifier.padding(innerPadding)
		) {
			// Home.
			composable(route = TodoListScreen.HOME.name) {
				TodoListScreen(
					modifier = Modifier.fillMaxSize(),
					todoItems = todoItems,
					onChangeChecked = { id, isChecked -> viewModel.updateCheckState(id, isChecked) }
				)
			}

			// Add.
			composable(route = TodoListScreen.ADD.name) {
				TodoItemAddScreen(
					modifier = Modifier.fillMaxSize(),
					popBackStack = { navController.popBackStack(TodoListScreen.HOME.name, inclusive = false) },
					onClickAdd = { contents -> viewModel.addTodoItem(contents) }
				)
			}
		}
	}
}
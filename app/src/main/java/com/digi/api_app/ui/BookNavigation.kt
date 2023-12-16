package com.digi.api_app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.digi.api_app.ui.screens.BookDetailScreen
import com.digi.api_app.ui.screens.BookSearchScreen

// screen
enum class Screen {
    Search,
    Detail
}

@Composable
fun BookNavigation(
    startDestination: String = Screen.Search.name
) {
    val viewModel = viewModel<BookViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Search.name) {
            BookSearchScreen(
                uiState = uiState,
                onQueryChange = viewModel::onQueryChange,
                onSubmit = {
                    viewModel.searchBooks() // search book in background
                    navController.navigate(Screen.Detail.name) // navigate to detail screen
                }
            )
        }
        composable(Screen.Detail.name) {
            BookDetailScreen(
                uiState = uiState,
                onBackPress = {
                    navController.popBackStack()
                },
                onBookSelected = {
                    // todo put it in UIState
                    // todo move to the display screen
                }
            )
        }
    }
}
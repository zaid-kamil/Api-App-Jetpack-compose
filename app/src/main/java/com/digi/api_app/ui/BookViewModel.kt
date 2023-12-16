package com.digi.api_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digi.api_app.network.BookApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState>
        get() = _uiState.asStateFlow()

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(queryText = query) }
    }

    // todo: add page number change

    fun searchBooks() {
        if (_uiState.value.queryText.isEmpty()){
            _uiState.update { it.copy(queryStatus = QueryStatus.IDLE) }
            return
        }
        _uiState.update { it.copy(queryStatus = QueryStatus.LOADING) }
        viewModelScope.launch {
            val bookData = BookApi.retrofitService.getBooks(
                _uiState.value.queryText,
                "10",
                "BOOKS"
            )
            _uiState.update {
                it.copy(
                    result = bookData.totalItems.toString(),
                    resultList = bookData.items,
                    queryStatus = QueryStatus.DONE
                )
            }
        }
    }
}
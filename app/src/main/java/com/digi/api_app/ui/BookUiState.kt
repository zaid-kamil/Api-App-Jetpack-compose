package com.digi.api_app.ui

import com.digi.api_app.data.Book

enum class QueryStatus {
    LOADING,
    ERROR,
    DONE,
    IDLE
}

data class BookUiState(
    val queryText: String = "",
    val result: String? = null,
    val resultList: List<Book> = emptyList(),
    val queryStatus: QueryStatus = QueryStatus.IDLE
)
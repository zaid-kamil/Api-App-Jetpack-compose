@file:OptIn(ExperimentalMaterial3Api::class)

package com.digi.api_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.digi.api_app.data.Book
import com.digi.api_app.ui.BookUiState

@Composable
fun BookDetailScreen(
    uiState: BookUiState,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onBookSelected:(Book) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = onBackPress) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            items(uiState.resultList) {
                BookCard(
                    book = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BookCard(book: Book, modifier: Modifier) {
    Card(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth()) {
        Text(
            text = book.volumeInfo?.title ?: "",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = (book.volumeInfo?.description?.slice(0..200) + "... Read more") ?: "",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = book.volumeInfo?.authors?.joinToString(", ") ?: "",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.labelSmall
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = book.volumeInfo?.publisher?.uppercase() ?: "")
            // todo: add a book display screen
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "next"
                )
            }
        }
    }
}

@Preview
@Composable
fun BookDetailScreenPreview() {
    BookDetailScreen(
        uiState = BookUiState(
            queryText = "Android",
            result = "Here is the result",
        ),
        onBackPress = { },
        onBookSelected = { }
    )
}
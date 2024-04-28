package com.tsdc.vinilos.album.view.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tsdc.vinilos.album.presentation.AlbumListViewModel

@Composable
fun AlbumListScreen(viewModel: AlbumListViewModel) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AlbumListBar()
        },
        contentColor = Color.Black
    ) { paddingValues ->
        Surface(color = Color.Black) {
            AlbumListContent(paddingValues, viewModel)
        }
    }
}

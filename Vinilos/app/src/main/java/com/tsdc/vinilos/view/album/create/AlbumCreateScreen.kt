package com.tsdc.vinilos.view.album.create

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun AlbumCreateScreen() {
    Scaffold(
        topBar = {
            AlbumCreateBar()
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        AlbumCreateContent(
            paddingValues = paddingValues,
            scrollState = scrollState
        )
    }
}
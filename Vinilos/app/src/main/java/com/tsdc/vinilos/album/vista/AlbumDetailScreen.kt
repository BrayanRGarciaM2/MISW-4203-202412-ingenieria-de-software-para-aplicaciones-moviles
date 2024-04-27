package com.tsdc.vinilos.album.vista

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.tsdc.vinilos.album.album2
import com.tsdc.vinilos.album.repository.Album

@Composable
fun AlbumDetailScreen(album: Album?) {
    Scaffold(
        topBar = {
            AlbumDetailBar(
                album = album
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        AlbumDetailContent(
            album = album ?: album2,
            paddingValues = paddingValues,
            scrollState = scrollState
        )
    }
}
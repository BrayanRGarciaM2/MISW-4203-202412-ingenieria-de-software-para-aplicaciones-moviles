package com.tsdc.vinilos.album.view.detail

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.tsdc.vinilos.data.model.Album

@Composable
fun AlbumDetailScreen(album: Album?) {
    Scaffold(
        modifier = Modifier.testTag("AlbumDetailScreen"),
        topBar = {
            AlbumDetailBar(
                album = album
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        AlbumDetailContent(
            album = album,
            paddingValues = paddingValues,
            scrollState = scrollState
        )
    }
}
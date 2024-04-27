package com.tsdc.vinilos.album.vista

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tsdc.vinilos.album.AlbumDetailContent
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

        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BoxWithConstraints {
                Surface(color = Color.Black) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        AlbumDetailContent(album ?: album2)
                    }
                }
            }
        }
    }
}
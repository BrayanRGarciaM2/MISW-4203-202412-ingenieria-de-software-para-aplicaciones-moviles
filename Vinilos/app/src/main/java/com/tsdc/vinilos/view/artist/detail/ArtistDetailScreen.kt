package com.tsdc.vinilos.view.artist.detail

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.tsdc.vinilos.data.model.Artist

@Composable
fun ArtistDetailScreen(artist: Artist?) {
    Scaffold(
        modifier = Modifier.testTag("ArtistDetailScreen"),
        topBar =
        {
            ArtistDetailToolbar()
        }
    )
    { paddingValues ->
        val scrollState = rememberScrollState()
        ArtistDetailContent(
            artist = artist,
            paddingValues = paddingValues,
            scrollState = scrollState
        )
    }
}
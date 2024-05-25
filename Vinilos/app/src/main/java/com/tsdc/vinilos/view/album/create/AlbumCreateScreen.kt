package com.tsdc.vinilos.view.album.create

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.tsdc.vinilos.presentation.album.AlbumCreateViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlbumCreateScreen(viewModel: AlbumCreateViewModel) {
    Scaffold(
        topBar = {
            AlbumCreateBar()
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        AlbumCreateContent(
            paddingValues = paddingValues,
            scrollState = scrollState,
            viewModel
        )
    }
}
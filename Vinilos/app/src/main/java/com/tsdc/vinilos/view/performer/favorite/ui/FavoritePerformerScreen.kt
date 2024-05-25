package com.tsdc.vinilos.view.performer.favorite.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tsdc.vinilos.presentation.performer.FavoritePerformerViewModel

@Composable
fun FavoritePerformerScreen(viewModel: FavoritePerformerViewModel) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FavoritePerformerToolBar()
        },
        contentColor = Color.Black
    ) { paddingValues ->
        Surface(color = Color.Black) {
            FavoritePerformerContent(paddingValues, viewModel)
        }
    }
}

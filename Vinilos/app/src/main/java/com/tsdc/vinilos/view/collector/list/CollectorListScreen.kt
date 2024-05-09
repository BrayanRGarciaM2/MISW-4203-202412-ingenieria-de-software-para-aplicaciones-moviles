package com.tsdc.vinilos.view.collector.list

import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.tsdc.vinilos.presentation.collector.CollectorListViewModel

@Composable
fun CollectorListScreen(viewModel: CollectorListViewModel) {

    Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        CollectorListToolBar()
    },
    contentColor = Color.Black
    ) { paddingValues ->
        Surface(color = Color.Black) {
            CollectorListContent(paddingValues, viewModel)
        }
    }
}
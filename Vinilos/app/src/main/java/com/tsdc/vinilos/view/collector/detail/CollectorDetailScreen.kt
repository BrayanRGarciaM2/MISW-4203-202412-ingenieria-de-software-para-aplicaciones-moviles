package com.tsdc.vinilos.view.collector.detail

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.tsdc.vinilos.data.model.Collector

@Composable
fun CollectorDetailScreen(collector: Collector?) {
    Scaffold(
        modifier = Modifier.testTag("CollectorDetailScreen"),
        topBar = {
            CollectorDetailBar(
                collector = collector
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        CollectorDetailContent(
            collector = collector,
            paddingValues = paddingValues,
            scrollState = scrollState
        )
    }
}
package com.tsdc.vinilos.view.collector.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.presentation.collector.CollectorListViewModel
import com.tsdc.vinilos.view.album.detail.AlbumDetailActivity
import com.tsdc.vinilos.view.collector.detail.CollectorDetailActivity
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CollectorListContent(paddingValues: PaddingValues, viewModel: CollectorListViewModel) {
    val collectors = listOf<Collector?>()
    var collectorToShow by remember {
        mutableStateOf(collectors)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrollState = rememberLazyListState()
    Column(Modifier.fillMaxSize()) {
        LaunchedEffect(viewModel) {
            launch {
                viewModel.getCollectors().observe(lifecycleOwner)  { result ->
                    when (result) {
                        is Output.Loading -> {
                            // Put a progress bar
                        }

                        is Output.Success -> {
                            // Show data
                            collectorToShow = result.data.toMutableList()
                        }

                        is Output.Failure<*> -> {
                            // Show error

                        }
                    }
                }
            }
        }
        LazyColumn(
            contentPadding = paddingValues,
            state = scrollState,
            modifier = Modifier
                .testTag("CollectorItem")
        ) {
            if (collectorToShow.size != 0) {
                items(collectorToShow.size) { collectorId ->
                    collectorToShow[collectorId]?.let { CollectorListItem(collector = it)}
                }
            } else {
                item {
                    Text(
                        text = "No se encontrarón colecciones para mostrar",
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                        modifier = Modifier.testTag("CollectorListError")
                    )
                }
            }
        }
    }
}

@Composable
fun CollectorListItem(collector: Collector) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .testTag("AlbumListItem")
            .clickable {
                ContextCompat.startActivity(
                    context,
                    CollectorDetailActivity.newIntent(context, collector),
                null
            )
        },
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.Black
        )
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = collector.name,
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color.White
            ),
        )
    }

}
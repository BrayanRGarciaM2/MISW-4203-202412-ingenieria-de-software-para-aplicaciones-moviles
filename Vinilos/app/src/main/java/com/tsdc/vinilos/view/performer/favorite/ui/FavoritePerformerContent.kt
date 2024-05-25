package com.tsdc.vinilos.view.performer.favorite.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.tsdc.vinilos.R
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.presentation.performer.FavoritePerformerViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoritePerformerContent(paddingValues: PaddingValues, viewModel: FavoritePerformerViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val performers = listOf<Artist?>()
    var performersToShow by remember {
        mutableStateOf(performers)
    }

    LaunchedEffect(viewModel) {
        launch {
            viewModel.getPerformerList().observe(lifecycleOwner) { result ->
                when (result) {
                    is Output.Loading -> {
                        // Put a progress bar
                    }

                    is Output.Success -> {
                        // Show data
                        performersToShow = result.data
                    }

                    is Output.Failure<*> -> {
                        // Show error
                    }
                }
            }
        }
    }
    FavoritePerformersListContent(paddingValues, performersToShow, viewModel)
}

@Composable
fun FavoritePerformersListContent(
    paddingValues: PaddingValues, artists: List<Artist?>,
    viewModel: FavoritePerformerViewModel
) {
    val context = LocalContext.current
    val checkedStates = remember { mutableStateListOf<Artist?>() }
    Column(modifier = Modifier.fillMaxHeight()) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            itemsIndexed(artists) { index, artist ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("FavoritePerformerListItem"),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedStates.contains(artist),
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                checkedStates.add(artist)
                            } else {
                                checkedStates.remove(artist)
                            }
                        }
                    )
                    Text(
                        text = artists[index]?.name.orEmpty(),
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .testTag("FavoritePerformerItemName")
                            .height(80.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )

                }
                Divider()
            }
        }
        val activity = LocalContext.current as? ComponentActivity
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                viewModel.addFavoritePerformers(checkedStates.toList())
                activity?.finish()
            }) {
            Text(
                text = getString(context, R.string.asociate_performer_text),
                color = Color.White
            )
        }
    }
}


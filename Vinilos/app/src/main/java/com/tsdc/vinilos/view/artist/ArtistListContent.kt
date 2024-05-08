package com.tsdc.vinilos.view.artist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import coil.compose.AsyncImage
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.presentation.artist.ArtistViewModel
import kotlinx.coroutines.launch

@Composable
fun ArtistListContent(paddingValues: PaddingValues, viewModel: ArtistViewModel) {
    val artist = mutableListOf<Artist?>()
    var artistToShow by remember {
        mutableStateOf(artist)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {
        LaunchedEffect(viewModel) {
            launch {
                viewModel.getArtists().observe(lifecycleOwner, Observer { result ->
                    when (result) {
                        is Output.Loading -> {
                            // Put a progress bar
                        }

                        is Output.Success -> {
                            // Show data
                            artistToShow = result.data.toMutableList()
                        }

                        is Output.Failure<*> -> {
                            // Show error

                        }
                    }
                })
            }
        }
        AsyncImage(
            modifier = Modifier
                .testTag("imagen")
                .width(80.dp)
                .height(80.dp)
                .padding(10.dp),
            model = artist.image,
            contentDescription = "Image"
        )
        Text(
            text = artist.name,
            fontSize = 20.sp,
            modifier = Modifier
                .testTag("name")
                .height(80.dp)
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
        LazyColumn(
            contentPadding = paddingValues,
            state = scrollState
        ) {
            if(artistToShow.size != 0){
                items(artistToShow.size) { artistId ->
                    artistToShow[id]?.let { ArtistListItem(artist = it) }
                }
            }else{
                item {
                    Text(
                        text = "No se encontraron artistas para mostrar",
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                        modifier = Modifier.testTag("ArtistListError")
                    )
                }
            }

        }
    }
}
package com.tsdc.vinilos.view.album.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.startActivity
import com.tsdc.vinilos.R
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.AlbumList
import com.tsdc.vinilos.presentation.album.AlbumListViewModel
import com.tsdc.vinilos.view.album.detail.AlbumDetailActivity
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun AlbumListContent(paddingValues: PaddingValues, viewModel: AlbumListViewModel) {
    val albums = AlbumList()
    var albumsToShow by remember {
        mutableStateOf(albums)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {
        LaunchedEffect(viewModel) {
            launch {
                viewModel.getAlbums().observe(lifecycleOwner) { result ->
                    when (result) {
                        is Output.Loading -> {
                            // Put a progress bar
                        }

                        is Output.Success -> {
                            // Show data
                            albumsToShow = result.data
                        }

                        is Output.Failure<*> -> {
                            // Show error

                        }
                    }
                }
            }
        }
        Text(
            text = getString(context, R.string.album_name_text),
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                letterSpacing = 0.15.sp,
                color = Color.White
            )
        )
        LazyColumn(
            contentPadding = paddingValues,
            state = scrollState
        ) {
            if (albumsToShow.results.isNotEmpty()) {
                items(albumsToShow.results.size) { albumId ->
                    AlbumListItem(album = albumsToShow.results[albumId])
                }
            } else {
                item {
                    Text(
                        text = "No se encontraron álbumes para mostrar",
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                        modifier = Modifier.testTag("AlbumListError")
                    )
                }
            }

        }
    }
}

@Composable
fun AlbumListItem(album: Album) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .testTag("AlbumListItem")
            .clickable {
                startActivity(
                    context,
                    AlbumDetailActivity.newIntent(context, album),
                    null
                )
            },
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.Black
        )
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .testTag("AlbumItemTitle"),
            text = album.name,
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color.White
            ),
        )
    }

}

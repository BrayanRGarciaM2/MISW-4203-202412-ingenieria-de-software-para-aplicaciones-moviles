package com.tsdc.vinilos.album.vista.list

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.startActivity
import com.tsdc.vinilos.R
import com.tsdc.vinilos.album.AlbumDetailActivity
import com.tsdc.vinilos.album.repository.data.model.Album

@Composable
fun AlbumListContent(paddingValues: PaddingValues, albums: List<Album>) {
    val albumsToShow = remember { albums }
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {
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
            items(albumsToShow.size) { albumId ->
                AlbumListItem(album = albums[albumId])
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
            modifier = Modifier.padding(16.dp),
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
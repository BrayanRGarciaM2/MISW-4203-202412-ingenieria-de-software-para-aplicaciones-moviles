package com.tsdc.vinilos.album.view.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.tsdc.vinilos.R
import com.tsdc.vinilos.album.album2
import com.tsdc.vinilos.album.data.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun AlbumDetailContent(album: Album?, paddingValues: PaddingValues, scrollState: ScrollState) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        BoxWithConstraints {
            Surface(color = Color.Black) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AlbumDetailMainContent(album ?: album2)
                    AlbumDetailSongs(album = album ?: album2)
                }
            }
        }
    }
}

@Composable
fun AlbumDetailMainContent(album: Album) {
    var bitmap by remember { mutableStateOf(null as Bitmap?) }

    LaunchedEffect(Unit) {
        launch {
            withContext(Dispatchers.IO) {
                val url = album.cover
                val stream = URL(url).openStream()
                bitmap = BitmapFactory.decodeStream(stream)
            }
        }
    }

    if (bitmap != null) {
        Image(
            painter = BitmapPainter(bitmap!!.asImageBitmap()),
            contentDescription = album.description,
            modifier = Modifier.size(200.dp)
        )
    }
    Text(
        text = album.name,
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        ),
        modifier = Modifier.padding(top = 16.dp)
    )
    Text(
        text = album.description,
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.White
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun AlbumDetailSongs(album: Album) {
    Column(Modifier.padding(top = 16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = getString(LocalContext.current, R.string.song_text),
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .padding(start = 16.dp)
            )
            Text(
                text = getString(LocalContext.current, R.string.duration_text),
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
        album.tracks.forEach { track ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = track.name,
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = track.duration,
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(end = 16.dp)
                )
            }
        }
    }
}
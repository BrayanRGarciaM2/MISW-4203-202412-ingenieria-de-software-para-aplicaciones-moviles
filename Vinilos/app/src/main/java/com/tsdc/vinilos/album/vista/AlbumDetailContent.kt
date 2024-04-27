package com.tsdc.vinilos.album.vista

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import com.tsdc.vinilos.album.album2
import com.tsdc.vinilos.album.repository.Album
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
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AlbumDetailPhoto(album ?: album2)
                }
            }
        }
    }
}

@Composable
fun AlbumDetailPhoto(album: Album) {
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
    //Text(text = album.performers.first().name, color = Color.Green)
}
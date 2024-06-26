package com.tsdc.vinilos.view.album.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.tsdc.vinilos.R
import com.tsdc.vinilos.data.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@SuppressLint("UnusedBoxWithConstraintsScope")
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
                    AlbumDetailMainContent(album)
                    AlbumDetailSongs(album = album)
                }
            }
        }
    }
}

@Composable
fun AlbumDetailMainContent(album: Album?) {
    var bitmap by remember { mutableStateOf(null as Bitmap?) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        launch {
            withContext(Dispatchers.IO) {
                val url = album?.cover
                if (url.isNullOrEmpty() || !isValidUrl(url)) {
                    errorMessage = "URL no válida"
                    return@withContext
                }
            try {
                val stream = URL(url).openStream()
                bitmap = BitmapFactory.decodeStream(stream)
                } catch (e: Exception) {
                    errorMessage = "Error al cargar la imagen"
                    e.message?.let { Log.e(errorMessage , it) }
                }
            }
        }
    }

    if (bitmap != null) {
        Image(
            painter = BitmapPainter(bitmap!!.asImageBitmap()),
            contentDescription = "Image of the album",
            modifier = Modifier
                .size(200.dp)
                .testTag("AlbumDetailImage")

        )
    }
    Text(
        text = album?.name.orEmpty(),
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        ),
        modifier = Modifier
            .padding(top = 16.dp)
            .testTag("AlbumTitle")
    )
    Text(
        text = album?.description.orEmpty(),
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.White
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .testTag("AlbumDetailDescription")
    )
}

fun isValidUrl(url: String): Boolean {
    return try {
        URL(url).toURI()
        true
    } catch (e: Exception) {
        false
    }
}

@Composable
fun AlbumDetailSongs(album: Album?) {
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
        album?.tracks?.forEach { track ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = track.name,
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .testTag("AlbumDetailSongTitle")
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
                        .testTag("AlbumDetailSongDuration")
                )
            }
        }
    }
}

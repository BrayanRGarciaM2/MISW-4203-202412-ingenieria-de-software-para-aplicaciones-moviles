package com.tsdc.vinilos.view.artist.detail

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tsdc.vinilos.data.model.Artist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ArtistDetailContent(artist: Artist?, paddingValues: PaddingValues, scrollState: ScrollState) {
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
                    ArtistDetailMainContent(artist)
                }
            }
        }
    }
}

@Composable
fun ArtistDetailMainContent(artist: Artist?) {
    var bitmap by remember { mutableStateOf(null as Bitmap?) }

    LaunchedEffect(Unit) {
        launch {
            withContext(Dispatchers.IO) {
                val url = artist?.image
                url?.let {
                    val stream = URL(url).openStream()
                    bitmap = BitmapFactory.decodeStream(stream)
                }
            }
        }
    }


    bitmap?.let {
        Image(
            painter = BitmapPainter(it.asImageBitmap()),
            contentDescription = artist?.description,
            modifier = Modifier
                .size(200.dp)
                .testTag("ArtistDetailImage")
        )
    }
    Text(
        text = artist?.name.orEmpty(),
        textAlign = TextAlign.Center,
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        ),
        modifier = Modifier
            .padding(top = 16.dp)
            .testTag("ArtistTitle")
    )
    Text(
        text = artist?.description.orEmpty(),
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.White
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .testTag("ArtistDetailDescription")
    )
}

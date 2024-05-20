package com.tsdc.vinilos.view.collector.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tsdc.vinilos.data.model.Collector
import java.text.SimpleDateFormat

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CollectorDetailContent(collector: Collector?, paddingValues: PaddingValues, scrollState: ScrollState) {
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
                    CollectorDetailMainContent(collector)
                    CollectorDetailAlbums(collector = collector)
                    CollectorDetailPerformers(collector = collector)
                }
            }
        }
    }
}


@Composable
fun CollectorDetailMainContent(collector: Collector?) {
    Text(
        text = "Datos del Coleccionista",
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        ),
        modifier = Modifier
            .padding(top = 16.dp)
            .testTag("CollectorTitle")
    )
    Text(
        text = "Correo: " + collector?.email.orEmpty(),
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.White
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(8.dp)
            .testTag("CollectorDetailEmail")
    )
    Text(
        text = "Teléfono: " + collector?.telephone.orEmpty(),
        color = Color.White,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.White
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(8.dp, 0.dp)
            .testTag("CollectorDetailTelephone")
    )
}
@Composable
fun CollectorDetailAlbums(collector: Collector?) {
    Column(Modifier.padding(top = 16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Albums de Colección",
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp, 5.dp)
                    .padding(top = 16.dp)
                    .padding(start = 16.dp)

            )
        }
        collector?.collectorAlbums?.forEach { track ->
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = track.id.toString(),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .testTag("CollectorDetailArtistTitle")
                )
                Text(
                    text = "Precio: " + track.price.toString(),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(16.dp, 5.dp)
                        .padding(end = 16.dp)
                        .testTag("PerformersDetailBirthDate")
                )
            }
        }
    }
}
@Composable
fun CollectorDetailPerformers(collector: Collector?) {
    Column(Modifier.padding(top = 16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Artistas favoritos",
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 16.dp)
                    .padding(start = 16.dp)

            )
        }
        collector?.favoritePerformers?.forEach { track ->
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate = formatter.format(track.birthDate)
            Row(Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier
                        .testTag("imagen")
                        .width(80.dp)
                        .height(80.dp)
                        .padding(10.dp),
                    model = track.image,
                    contentDescription = "Image"
                )
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        text = track.name,
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .padding(16.dp, 5.dp)
                            .testTag("CollectorDetailArtistTitle")
                    )
                    Text(
                        text = formattedDate,
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .padding(16.dp, 5.dp)
                            .padding(end = 16.dp)
                            .testTag("PerformersDetailBirthDate")
                    )
                }
            }
        }
    }
}
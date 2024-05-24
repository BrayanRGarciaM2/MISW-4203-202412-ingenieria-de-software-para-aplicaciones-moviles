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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.presentation.collector.detail.CollectorDetailViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CollectorDetailContent(collector: Collector?, viewModel: CollectorDetailViewModel,
                           paddingValues: PaddingValues, scrollState: ScrollState) {
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
                    Divider(
                        thickness = 2.dp
                    )
                    CollectorDetailMainContent(collector)
                    Divider(
                        modifier = Modifier.padding(top = 16.dp),
                        thickness = 2.dp
                    )
                    CollectorDetailAlbums(collector = collector, viewModel = viewModel)
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
            .testTag("CollectorDetailTitle")
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
fun CollectorDetailAlbums(collector: Collector?, viewModel: CollectorDetailViewModel) {
    var albumDetail by remember { mutableStateOf(null as Album?) }
    val lifecycleOwner = LocalLifecycleOwner.current
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
                    .padding(20.dp, 5.dp)
                    .padding(top = 16.dp)
                    .padding(start = 16.dp)

            )
        }
        collector?.collectorAlbums?.forEach { album ->
            Column(Modifier.fillMaxWidth()) {
                LaunchedEffect(viewModel) {
                    launch {
                        viewModel.getAlbumById(album.id).observe(lifecycleOwner) { result ->
                            when (result) {
                                is Output.Loading -> {
                                    // Put a progress bar
                                }

                                is Output.Success -> {
                                    // Show data
                                    albumDetail = result.data
                                }

                                is Output.Failure<*> -> {
                                    // Show error

                                }
                            }
                        }
                    }
                }
                AlbumDetailInformation(albumDetail, album.price)
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
                    .padding(20.dp, 5.dp)
                    .padding(top = 16.dp)
                    .padding(start = 16.dp)

            )
        }
        collector?.favoritePerformers?.forEach { track ->
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val date = track?.birthDate ?: track?.creationDate;
            val formattedDate = formatter.format(date)
            Row(Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier
                        .testTag("CollectorFavoritePerformersImage")
                        .width(80.dp)
                        .height(80.dp)
                        .padding(10.dp),
                    model = track.image,
                    contentDescription = "Imagen del artista favorito ${track?.name.orEmpty()}"
                )
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        text = track.name,
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .padding(16.dp, 5.dp)
                            .testTag("CollectorDetailFavoritePerformersName")
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
                            .testTag("CollectorDetailFavoritePerformersDate")
                    )
                }
            }
        }
    }
}

@Composable
fun AlbumDetailInformation(album : Album?, price : Int){
    Row(Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .testTag("CollectorDetailAlbumImage")
                .width(80.dp)
                .height(80.dp)
                .padding(10.dp),
            model = album?.cover,
            contentDescription = "Imagen del álbum ${album?.name.orEmpty()}"
        )
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = album?.name.orEmpty(),
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp, 5.dp)
                    .testTag("CollectorDetailAlbumName")
            )
            Text(
                text = "Precio: $price",
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp, 5.dp)
                    .padding(end = 16.dp)
                    .testTag("CollectorDetailAlbumPrice")
            )
        }
    }
}





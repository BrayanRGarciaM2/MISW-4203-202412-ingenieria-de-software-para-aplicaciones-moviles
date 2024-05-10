package com.tsdc.vinilos.view.album.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.view.utils.ActionItems
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailBar(album: Album?) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = album?.performers?.first()?.name.orEmpty(),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = "Álbum " + getYearFromDate(album?.releaseDate),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        letterSpacing = 0.15.sp,
                        color = Color.White
                    )
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black),
        navigationIcon = { ActionItems() }
    )
}

fun getYearFromDate(date: Date?): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date ?: Date()
    return calendar.get(Calendar.YEAR)
}

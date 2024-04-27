package com.tsdc.vinilos.album.vista

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.tsdc.vinilos.R
import com.tsdc.vinilos.album.repository.Album
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailBar(album: Album?) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = album?.performers?.first()?.name.orEmpty(),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = "Álbum " + getYearFromDate(album?.releaseDate),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
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

@Composable
fun ActionItems() {
    val activity = LocalContext.current as? ComponentActivity
    // IconButton se beneficia del RowScope para ser colocado dentro de un Row
    IconButton(onClick = { activity?.finish() }) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = ContextCompat.getString(
                LocalContext.current,
                R.string.back_button_text
            ),
            modifier = Modifier
                .size(60.dp),
            tint = Color.White
        )
    }
}
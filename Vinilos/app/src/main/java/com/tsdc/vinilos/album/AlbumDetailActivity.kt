package com.tsdc.vinilos.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tsdc.vinilos.album.repository.Album
import com.tsdc.vinilos.album.repository.Artist
import com.tsdc.vinilos.album.ui.theme.VinilosTheme
import com.tsdc.vinilos.album.vista.AlbumDetailScreen
import java.util.Date

val album2 = Album(
    0,
    "hola",
    "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
    Date(),
    "",
    "",
    "",
    listOf(
        Artist(
            0,
            "Ruben Blades",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "",
            Date()
        )
    )
)

class AlbumDetailActivity : ComponentActivity() {
    private val album: Album? by lazy {
        intent?.getParcelableExtra(ALBUM_ID) as? Album
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlbumDetailScreen(album)
                }
            }
        }
    }

    companion object {
        private const val ALBUM_ID = "album_id"
        fun newIntent(context: Context, album: Album) =
            Intent(context, AlbumDetailActivity::class.java).apply {
                putExtra(ALBUM_ID, album)
            }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    VinilosTheme {
        AlbumDetailScreen(album = album2)
    }
}
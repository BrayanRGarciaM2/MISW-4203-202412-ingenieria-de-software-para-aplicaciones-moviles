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
import com.tsdc.vinilos.album.data.model.Album
import com.tsdc.vinilos.album.data.model.Artist
import com.tsdc.vinilos.album.data.model.Song
import com.tsdc.vinilos.album.ui.theme.VinilosTheme
import com.tsdc.vinilos.album.view.detail.AlbumDetailScreen
import java.util.Date

val album2 = Album(
    0,
    "hola",
    "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
    Date(),
    "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
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
    ),
    listOf(
        Song(0, "Desapariciones", "5:00"),
        Song(1, "Desapariciones 2", "5:00"),
        Song(2, "Desapariciones 3", "5:00"),
        Song(3, "Desapariciones 4", "5:00"),
        Song(4, "Desapariciones 5", "5:00"),
        Song(5, "Desapariciones 6", "5:00"),
        Song(6, "Desapariciones 7", "5:00"),
        Song(7, "Desapariciones 8", "5:00"),
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
        fun newIntent(context: Context, album: Album): Intent =
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
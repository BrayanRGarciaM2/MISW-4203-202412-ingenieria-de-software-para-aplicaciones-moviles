package com.tsdc.vinilos.view.artist.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.view.artist.detail.ui.theme.VinilosTheme

class ArtistDetailActivity : ComponentActivity() {
    @Suppress("DEPRECATION")
    private val artist: Artist? by lazy {
        intent?.getParcelableExtra(ARTIST_ID) as? Artist
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
                    ArtistDetailScreen(artist)
                }
            }
        }
    }

    companion object {
        private const val ARTIST_ID = "artist_id"
        fun newIntent(context: Context, album: Artist?): Intent =
            Intent(context, ArtistDetailActivity::class.java).apply {
                putExtra(ARTIST_ID, album)
            }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VinilosTheme {
        Greeting("Android")
    }
}
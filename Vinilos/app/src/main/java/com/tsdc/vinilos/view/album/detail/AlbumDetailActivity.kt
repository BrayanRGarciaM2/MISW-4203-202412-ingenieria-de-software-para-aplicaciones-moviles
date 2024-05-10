package com.tsdc.vinilos.view.album.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.ui.theme.VinilosTheme


class AlbumDetailActivity : ComponentActivity() {
    @Suppress("DEPRECATION")
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
                    Log.d("Pruebaaa", album.toString())
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

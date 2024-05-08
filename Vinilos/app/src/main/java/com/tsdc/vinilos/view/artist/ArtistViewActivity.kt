package com.tsdc.vinilos.view.artist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tsdc.vinilos.data.remote.RemoteArtistDataSource
import com.tsdc.vinilos.presentation.artist.ArtistListViewModelFactory
import com.tsdc.vinilos.presentation.artist.ArtistViewModel
import com.tsdc.vinilos.repository.artist.ArtistRepositoryImpl
import com.tsdc.vinilos.ui.theme.VinilosTheme

class ArtistViewActivity : ComponentActivity() {


    val viewModel by viewModels<ArtistViewModel> {
        ArtistListViewModelFactory(
            ArtistRepositoryImpl(
                RemoteArtistDataSource()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    ArtistListScreen(viewModel)
                }
            }
        }
    }
}
package com.tsdc.vinilos.album

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tsdc.vinilos.album.data.remote.RemoteAlbumDataSource
import com.tsdc.vinilos.album.presentation.AlbumListViewModel
import com.tsdc.vinilos.album.presentation.AlbumListViewModelFactory
import com.tsdc.vinilos.album.repository.AlbumRepositoryImpl
import com.tsdc.vinilos.album.ui.theme.VinilosTheme
import com.tsdc.vinilos.album.view.list.AlbumListScreen

class AlbumListActivity : ComponentActivity() {

    val viewModel by viewModels<AlbumListViewModel> {
        AlbumListViewModelFactory(
            AlbumRepositoryImpl(
                RemoteAlbumDataSource()
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
                    AlbumListScreen(viewModel)
                }
            }
        }
    }
}

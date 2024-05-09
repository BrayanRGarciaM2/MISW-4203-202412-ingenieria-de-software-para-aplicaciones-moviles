package com.tsdc.vinilos.view.album.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tsdc.vinilos.album.view.list.AlbumListScreen
import com.tsdc.vinilos.data.remote.album.RemoteAlbumDataSource
import com.tsdc.vinilos.presentation.album.AlbumListViewModel
import com.tsdc.vinilos.presentation.album.AlbumListViewModelFactory
import com.tsdc.vinilos.repository.album.AlbumRepositoryImpl
import com.tsdc.vinilos.ui.theme.VinilosTheme

class AlbumListActivity : ComponentActivity() {

    private val viewModel by viewModels<AlbumListViewModel> {
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

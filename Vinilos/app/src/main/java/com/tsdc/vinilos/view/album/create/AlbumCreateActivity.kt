package com.tsdc.vinilos.view.album.create

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.tsdc.vinilos.data.local.album.LocalAlbumDataSource
import com.tsdc.vinilos.data.local.database.VynilsDatabase
import com.tsdc.vinilos.data.remote.album.RemoteAlbumDataSource
import com.tsdc.vinilos.presentation.album.AlbumCreateViewModel
import com.tsdc.vinilos.presentation.album.AlbumCreateViewModelFactory
import com.tsdc.vinilos.repository.album.AlbumRepositoryImpl
import com.tsdc.vinilos.ui.theme.VinilosTheme

class AlbumCreateActivity : ComponentActivity() {


    private val viewModel by viewModels<AlbumCreateViewModel> {
        AlbumCreateViewModelFactory(
            AlbumRepositoryImpl(
                application,
                RemoteAlbumDataSource(),
                LocalAlbumDataSource(
                    VynilsDatabase.getDatabase(applicationContext).albumDao()
                )
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
                AlbumCreateScreen(viewModel)
            }
        }
    }
}
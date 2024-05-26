package com.tsdc.vinilos.view.performer.favorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tsdc.vinilos.data.local.login.LocalLoginDataSource
import com.tsdc.vinilos.data.remote.artist.ArtistRemoteDataSource
import com.tsdc.vinilos.data.remote.login.RemoteLoginDataSource
import com.tsdc.vinilos.data.remote.performer.PerformerRemoteDataSource
import com.tsdc.vinilos.presentation.performer.FavoritePerformerViewModel
import com.tsdc.vinilos.presentation.performer.FavoritePerformerViewModelFactory
import com.tsdc.vinilos.repository.artist.ArtistRepositoryImpl
import com.tsdc.vinilos.repository.login.LoginRepositoryImpl
import com.tsdc.vinilos.repository.performer.PerformersRepositoryImpl
import com.tsdc.vinilos.view.performer.favorite.theme.VinilosTheme
import com.tsdc.vinilos.view.performer.favorite.ui.FavoritePerformerScreen

class FavoritePerformerActivity : ComponentActivity() {
    private val viewModel by viewModels<FavoritePerformerViewModel> {
        FavoritePerformerViewModelFactory(
            repo = ArtistRepositoryImpl(
                artistRemoteDataSource = ArtistRemoteDataSource()
            ),
            performersRepo = PerformersRepositoryImpl(
                performerRemoteDataSource = PerformerRemoteDataSource()
            ),
            loginRepository = LoginRepositoryImpl(
                dataSourceLocal = LocalLoginDataSource(this),
                dataSourceRemote = RemoteLoginDataSource()
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
                    color = MaterialTheme.colorScheme.background
                ) {
                    FavoritePerformerScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    VinilosTheme {
        Greeting2("Android")
    }
}

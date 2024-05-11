package com.tsdc.vinilos.view.artist.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.data.remote.artist.ArtistRemoteDataSource
import com.tsdc.vinilos.presentation.artist.ArtistListViewModelFactory
import com.tsdc.vinilos.presentation.artist.ArtistViewModel
import com.tsdc.vinilos.repository.artist.ArtistRepositoryImpl
import com.tsdc.vinilos.ui.theme.VinilosTheme
import com.tsdc.vinilos.view.artist.detail.ArtistDetailActivity
import kotlinx.coroutines.launch

class ArtistViewActivity : ComponentActivity() {

    private val viewModel by viewModels<ArtistViewModel> {
        ArtistListViewModelFactory(
            repo = ArtistRepositoryImpl(
                artistRemoteDataSource = ArtistRemoteDataSource()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitArtistView(viewModel)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun InitArtistView(vm: ArtistViewModel) {

        val artists = listOf<Artist?>()
        val error = true
        var artistsToShow by remember {
            mutableStateOf(artists)
        }
        var errorOcurred by remember {
            mutableStateOf(error)
        }
        val lifecycleOwner = LocalLifecycleOwner.current


        VinilosTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Artistas") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            navigationIcon = {
                                IconButton(onClick = {
                                    super.finish()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowLeft,
                                        contentDescription = "Botón de volver"
                                    )
                                }
                            }
                        )
                    },
                    content = {
                        LaunchedEffect(vm) {
                            launch {
                                vm.getArtistList().observe(lifecycleOwner) { result ->
                                    when (result) {
                                        is Output.Loading -> {
                                            // Put a progress bar
                                        }

                                        is Output.Success -> {
                                            // Show data
                                            artistsToShow = result.data
                                            errorOcurred = false
                                        }

                                        is Output.Failure<*> -> {
                                            // Show error
                                            errorOcurred = true
                                        }
                                    }
                                }
                            }
                        }

                        if (artistsToShow.isNotEmpty()) {
                            ArtistListContent(paddingValues = it, artistsToShow)
                        } else {
                            ArtistListError(it)
                        }

                    }
                )
            }
        }
    }

    @Composable
    fun ArtistListContent(paddingValues: PaddingValues, artists: List<Artist?>) {
        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
        ) {
            items(artists.size) { artistPosition ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("ArtistaListItem")
                        .clickable {
                            startActivity(
                                ArtistDetailActivity.newIntent(context, artists[artistPosition]),
                                null
                            )
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .testTag("imagen")
                            .width(80.dp)
                            .height(80.dp)
                            .padding(10.dp),
                        model = artists[artistPosition]?.image,
                        contentDescription = "Image"
                    )
                    Text(
                        text = artists[artistPosition]?.name.orEmpty(),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .testTag("artistName")
                            .height(80.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )

                }
                Divider()
            }
        }
    }

    @Composable
    fun ArtistListError(paddingValues: PaddingValues) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "No se encontraron artistas para mostrar",
                    style = TextStyle(
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.testTag("ArtistaListError")
                )
            }
        }
    }
}





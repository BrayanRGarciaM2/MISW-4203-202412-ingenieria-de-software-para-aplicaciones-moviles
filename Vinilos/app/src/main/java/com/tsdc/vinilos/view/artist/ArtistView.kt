package com.tsdc.vinilos.view.artist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tsdc.vinilos.presentation.artist.ArtistViewModel
import com.tsdc.vinilos.ui.theme.VinilosTheme

class ArtistViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = ArtistViewModel()
        super.onCreate(savedInstanceState )
        setContent {
            InitArtistView(vm)
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun InitArtistView(vm: ArtistViewModel) {

        //var localContext = LocalContext.current

        LaunchedEffect(Unit, block = {
            vm.getArtistList()
        })

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
                        if (vm.errorMessage.isEmpty() && vm.artistList.isNotEmpty()) {
                            LazyColumn(modifier = Modifier
                                .fillMaxHeight()
                                .padding(it)) {
                                items(vm.artistList) { artist ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .testTag("ArtistaListItem")
                                    ) {
                                        AsyncImage(
                                            modifier = Modifier
                                                .testTag("imagen")
                                                .width(80.dp)
                                                .height(80.dp)
                                                .padding(10.dp),
                                            model = artist.image,
                                            contentDescription = "Image"
                                        )
                                        Text(
                                            text = artist.name,
                                            fontSize = 20.sp,
                                            modifier = Modifier
                                                .testTag("name")
                                                .height(80.dp)
                                                .fillMaxWidth()
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                        )

                                    }
                                    Divider()
                                }
                            }
                        } else {
                            LazyColumn(modifier = Modifier
                                .fillMaxHeight()
                                .padding(it)) {
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
                )
            }
        }
    }

}





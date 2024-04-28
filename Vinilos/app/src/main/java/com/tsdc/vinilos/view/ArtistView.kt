package com.tsdc.vinilos.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tsdc.vinilos.modelView.ArtistViewModel
import com.tsdc.vinilos.ui.theme.VinilosTheme
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController

class ArtistViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = ArtistViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            initArtistView(vm)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun initArtistView(vm: ArtistViewModel) {

    val navController = rememberNavController()

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
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Botón de volver"
                                )
                            }
                        }
                    )
                },
                content = {
                    if (vm.errorMessage.isEmpty() && vm.artistList.isNotEmpty()) {
                        Column(modifier = Modifier.padding(it)) {
                            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                                items(vm.artistList) { todo ->
                                    Column {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                            ) {
                                                Text(
                                                    todo.name,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(16.dp))
                                            Text(
                                                todo.description,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )     }
                                        Divider()
                                    }
                                }
                            }
                        }
                    } else {
                        Text(vm.errorMessage)
                        Log.d("Error Mensaje", vm.errorMessage)
                    }

                }
            )
        }
    }
}
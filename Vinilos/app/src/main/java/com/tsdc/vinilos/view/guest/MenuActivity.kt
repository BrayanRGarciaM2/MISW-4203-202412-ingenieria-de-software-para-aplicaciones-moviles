package com.tsdc.vinilos.view.guest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.tsdc.vinilos.MainActivity
import com.tsdc.vinilos.R
import com.tsdc.vinilos.ui.theme.VinilosTheme
import com.tsdc.vinilos.view.album.list.AlbumListActivity
import com.tsdc.vinilos.view.artist.list.ArtistViewActivity

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitMenuActivity()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitMenuActivity() {
    val localContext = LocalContext.current

    VinilosTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 97.dp),
                    painter = painterResource(id = R.mipmap.vinilos_icon),
                    contentDescription = "Icono de Vinilos"
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                ) {
                    LazyColumn {
                        item {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = {
                                    startActivity(
                                        localContext,
                                        Intent(localContext, AlbumListActivity::class.java),
                                        null
                                    )
                                }
                            ) {
                                Text(text = "Albumes", color = Color.White)
                            }
                        }
                        item {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = {
                                    localContext.startActivity(
                                        Intent(
                                            localContext,
                                            ArtistViewActivity::class.java
                                        )
                                    )
                                }
                            ) {
                                Text(text = "Artistas", color = Color.White)
                            }
                        }
                        item {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "Coleccionistas", color = Color.White)
                            }
                        }
                        item {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = {
                                    localContext.startActivity(
                                        Intent(
                                            localContext,
                                            MainActivity::class.java
                                        )
                                    )
                                }
                            ) {
                                Text(text = "Volver", color = Color.White)
                            }
                        }
                    }
                }

            }
        }
    }
}
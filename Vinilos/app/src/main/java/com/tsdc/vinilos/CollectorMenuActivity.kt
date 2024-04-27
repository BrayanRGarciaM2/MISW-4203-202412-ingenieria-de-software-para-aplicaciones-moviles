package com.tsdc.vinilos

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tsdc.vinilos.ui.theme.VinilosTheme

class CollectorMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            initCollectorMenuActivity()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun initCollectorMenuActivity() {
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
                    painter = painterResource(id = R.drawable.vinilos_icon),
                    contentDescription = "Icono de Vinilos"
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                ) {
                    LazyColumn {
                        item {
                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "Albumes")
                            }
                        }
                        item {
                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = { localContext.startActivity(Intent(localContext, ArtistsActivity::class.java))  }
                            ) {
                                Text(text = "Artistas")
                            }
                        }
                        item {
                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "Coleccionistas")
                            }
                        }

                        item {
                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "Agregar artista")
                            }
                        }
                        item {
                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "Crear un álbum")
                            }
                        }
                        item {
                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp, vertical = 10.dp),
                                onClick = { localContext.startActivity(Intent(localContext, MainActivity::class.java)) }
                            ) {
                                Text(text = "Volver")
                            }
                        }
                    }
                }

            }
        }
    }
}
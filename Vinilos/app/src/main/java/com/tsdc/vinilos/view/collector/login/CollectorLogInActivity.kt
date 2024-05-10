package com.tsdc.vinilos.view.collector.login

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tsdc.vinilos.R
import com.tsdc.vinilos.ui.theme.VinilosTheme

class CollectorLogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitLoginActivity()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitLoginActivity() {
    val localContext = LocalContext.current
    var collectorUser by remember {
        mutableStateOf("")
    }
    VinilosTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn {
                item {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(top = 97.dp),
                        painter = painterResource(id = R.drawable.vinilos_icon),
                        contentDescription = "Icono de Vinilos")
                }
                item {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp)
                    ){
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                value = collectorUser,
                                onValueChange = {collectorUser = it},
                                label = { Text(text = "Digíte su correo")}
                            )
                            Button(
                                onClick = { localContext.startActivity(Intent(localContext, CollectorMenuActivity::class.java)) },
                                modifier = Modifier.padding(top = 20.dp)
                            ){
                                Text(text = "Identificarse")
                            }
                        }

                    }

                }
            }
        }
    }
}
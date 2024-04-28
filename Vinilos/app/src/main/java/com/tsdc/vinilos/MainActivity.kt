package com.tsdc.vinilos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.getString
import com.tsdc.vinilos.ui.theme.VinilosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickRolesActivity()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PickRolesActivity(){
    val localContext = LocalContext.current

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
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp)
                    ){
                        //Botón Rol visitante
                        Button(
                            onClick = {
                                localContext.startActivity(Intent(localContext, MenuActivity::class.java))
                            }

                        ){
                            Text(text = "Visitante")
                        }
                        //Botón Rol coleccionista
                        Button(
                            onClick = { localContext.startActivity(Intent(localContext, CollectorLogInActivity::class.java)) }
                        ){
                            Text(text = "Coleccionista")
                        }
                    }
                }
            }
        }
    }
}

/*
@Composable
fun MainView() {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(color = Color(R.color.black))
    ) {
        val (image, visitorButton, collectorButton) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.logo_vinilos),
            contentDescription = stringResource(
                id = R.string.logo_content_description
            ),
            modifier = Modifier
                .size(125.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, 97.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Button(onClick = { }, modifier = Modifier
            .constrainAs(visitorButton) {
                top.linkTo(image.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(collectorButton.start)
            }) {
            Text(text = getString(LocalContext.current, R.string.visitor_text))
        }
        Button(onClick = { }, modifier = Modifier
            .constrainAs(collectorButton) {
                top.linkTo(image.bottom, 16.dp)
                start.linkTo(visitorButton.end)
                end.linkTo(parent.end)
            }) {
            Text(text = getString(LocalContext.current, R.string.collector_text))
        }
    }
}*/

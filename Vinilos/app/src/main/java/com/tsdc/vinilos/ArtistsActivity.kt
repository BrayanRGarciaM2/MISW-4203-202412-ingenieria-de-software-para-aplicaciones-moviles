package com.tsdc.vinilos

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Response
import com.tsdc.vinilos.brokers.ArtistsBroker
import com.tsdc.vinilos.ui.theme.VinilosTheme
import org.json.JSONArray

class ArtistsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            initArtistActivity()

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun initArtistActivity() {
    VinilosTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Artistas") },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                content = { it }
            )
        }
    }
}


@Preview
@Composable
fun test() {
    Text(text = "Prueba")
}
@Preview
@Composable
fun getArtistList() {
    lateinit var artistBroker: ArtistsBroker
    var data:String = "Hello"
    artistBroker = ArtistsBroker(LocalContext.current)

    artistBroker.instance.add(
        ArtistsBroker.getRequest("musicians",
        Response.Listener<String> { response ->
            // Display the first 500 characters of the response string.
            data = response
            Log.d("Funcionaaa", response)
        },
        Response.ErrorListener {
            data = "error"
            Log.d("ERRORRRRRR", it.toString())
        }
    ))

    Text(text = data)

}
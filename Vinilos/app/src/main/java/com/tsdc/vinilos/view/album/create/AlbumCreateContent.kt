package com.tsdc.vinilos.view.album.create

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

import com.tsdc.vinilos.view.collector.login.CollectorMenuActivity
import java.time.LocalDate
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun AlbumCreateContent(paddingValues: PaddingValues, scrollState: ScrollState) {
    val localContext = LocalContext.current
    var nombre by remember {
        mutableStateOf("")
    }
    var descripción by remember {
        mutableStateOf("")
    }
    var collectorUser by remember {
        mutableStateOf("")
    }
    var género by remember {
        mutableStateOf("")
    }
    var imagen by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        BoxWithConstraints {
            Surface(color = Color.Black) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = nombre,
                        onValueChange = {nombre = it},
                        label = { Text(text = "Nombre") }
                    )
                    TextField(
                        value = descripción,
                        onValueChange = {descripción = it},
                        label = { Text(text = "Descripción") }
                    )
                    TextField(
                        value = género,
                        onValueChange = {género = it},
                        label = { Text(text = "Género") }
                    )
                    TextField(
                        value = imagen,
                        onValueChange = {imagen = it},
                        label = { Text(text = "Imagen") }
                    )
                    TextField(
                        value = collectorUser,
                        onValueChange = {collectorUser = it},
                        label = { Text(text = "Compañia discografica") }
                    )

                    Button(
                        onClick = { localContext.startActivity(Intent(localContext, CollectorMenuActivity::class.java)) },
                        modifier = Modifier.padding(top = 20.dp)
                    ){
                        Text(text = "Registrar álbum")
                    }
                }
            }
        }
    }
}

object DateFormatter : MaskFormatter {
    override fun format(textToFormat: String): String {
        TODO("Format '01212022' into '01/21/2022'")
    }
}

internal fun MaskFormatter.toVisualTransformation(): VisualTransformation =
    VisualTransformation {
        val output = format(it.text)
        TransformedText(
            AnnotatedString(output),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = output.length
                override fun transformedToOriginal(offset: Int): Int = it.text.length
            }
        )
    }
package com.tsdc.vinilos.view.album.create

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.tsdc.vinilos.R
import com.tsdc.vinilos.ui.theme.VinilosTheme

import com.tsdc.vinilos.view.collector.login.CollectorMenuActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    var compañia by remember {
        mutableStateOf("")
    }
    var género by remember {
        mutableStateOf("")
    }
    var imagen by remember {
        mutableStateOf("")
    }
    val date = remember { mutableStateOf(LocalDate.now())}

    var nombreError by remember { mutableStateOf(false) }
    var descripciónError by remember { mutableStateOf(false) }
    var géneroError by remember { mutableStateOf(false) }
    var imagenError by remember { mutableStateOf(false) }
    var compañiaError by remember { mutableStateOf(false) }

    fun validateFields() {
        nombreError = nombre.isBlank()
        descripciónError = descripción.isBlank()
        géneroError = género.isBlank()
        imagenError = imagen.isBlank()
        compañiaError = compañia.isBlank()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        VinilosTheme {
            Surface(color = Color.Black) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {
                            nombre = it
                            nombreError = it.isBlank()
                        },
                        label = { Text(text = "Nombre", color = colorResource(id = R.color.label_text_color))},
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (nombreError) {
                        Text(text = "Campo nombre es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = descripción,
                        onValueChange = {descripción = it},
                        label = { Text(text = "Descripción", color = colorResource(id = R.color.label_text_color))},
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (descripciónError) {
                        Text(text = "Campo descripción es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = género,
                        onValueChange = {género = it},
                        label = { Text(text = "Género", color = colorResource(id = R.color.label_text_color)) },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (géneroError) {
                        Text(text = "Campo género es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = imagen,
                        onValueChange = {imagen = it},
                        label = { Text(text = "Imagen", color = colorResource(id = R.color.label_text_color)) },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (imagenError) {
                        Text(text = "Campo imagen es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = compañia,
                        onValueChange = {compañia = it},
                        label = { Text(text = "Compañia discografica", color = colorResource(id = R.color.label_text_color)) },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (compañiaError) {
                        Text(text = "Campo Compañia es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    CustomDatePicker(
                        value = date.value,
                        onValueChange = {date.value = it}
                    )
                    Button(
                        onClick = {

                            validateFields()

                            if (!nombreError && !descripciónError && !géneroError && !imagenError && !compañiaError) {
                                localContext.startActivity(Intent(localContext, CollectorMenuActivity::class.java))
                            }
                        },
                        modifier = Modifier.padding(top = 20.dp)
                    ){
                        Text(text = "Registrar álbum")
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit
) {

    val open = remember { mutableStateOf(false)}

    if (open.value) {
        CalendarDialog(
            state = rememberUseCaseState(visible = true, true, onCloseRequest = { open.value = false } ),
            config = CalendarConfig(
                yearSelection = true,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
                selectedDate = value
            ) { newDate ->
                onValueChange(newDate)
            },
        )
    }

    OutlinedTextField(
        modifier = Modifier.clickable {
            open.value = true
        }.padding(top = 20.dp),
        enabled = false,
        label = { Text(text = "Fecha de lanzamiento") },
        value = value.format(DateTimeFormatter.ISO_DATE),
        onValueChange = {}
    )
}




package com.tsdc.vinilos.view.album.create

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.google.gson.JsonObject
import com.tsdc.vinilos.R
import com.tsdc.vinilos.presentation.album.AlbumCreateViewModel
import com.tsdc.vinilos.ui.theme.VinilosTheme

import com.tsdc.vinilos.view.utils.CustomDatePicker
import com.tsdc.vinilos.view.utils.hideKeyBoard
import com.tsdc.vinilos.view.utils.isValidImageUrl
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class AlbumFormData(
    val name: String,
    val description: String,
    val recordLabel: String,
    val genre: String,
    val cover: String,
    val releaseDate: String)
{
    fun toJsonObject() = JsonObject().apply {
        addProperty("name", name)
        addProperty("description", description)
        addProperty("recordLabel", recordLabel)
        addProperty("genre", genre)
        addProperty("cover", cover)
        addProperty("releaseDate", releaseDate)
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun AlbumCreateContent(paddingValues: PaddingValues, scrollState: ScrollState, viewModel: AlbumCreateViewModel) {
    var name by remember { mutableStateOf("")}
    var description by remember { mutableStateOf("")}
    var recordLabel by remember { mutableStateOf("")}
    var genre by remember { mutableStateOf("")}
    var cover by remember { mutableStateOf("")}

    var nameError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var genreError by remember { mutableStateOf(false) }
    var coverError by remember { mutableStateOf(false) }
    var recordLabelError by remember { mutableStateOf(false) }

    val releaseDate = remember { mutableStateOf(LocalDate.now())}
    val view = LocalView.current

    fun validateFields() {
        nameError = name.isBlank()
        descriptionError = description.isBlank()
        genreError = genre.isBlank()
        coverError = cover.isBlank()
        recordLabelError = recordLabel.isBlank()
    }

    val formData = remember { mutableStateOf(AlbumFormData("", "", "", "","", "")) }


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
                        value = name,
                        onValueChange = {
                            name = it
                            nameError = it.isBlank()
                        },
                        label = { Text(text = "Nombre", color = colorResource(id = R.color.label_text_color))},
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (nameError) {
                        Text(text = "Campo nombre es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = description,
                        onValueChange = {description = it},
                        label = { Text(text = "Descripción", color = colorResource(id = R.color.label_text_color))},
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (descriptionError) {
                        Text(text = "Campo descripción es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = genre,
                        onValueChange = {genre = it},
                        label = { Text(text = "Género", color = colorResource(id = R.color.label_text_color)) },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (genreError) {
                        Text(text = "Campo género es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = cover,
                        onValueChange = {cover = it},
                        label = { Text(text = "Imagen", color = colorResource(id = R.color.label_text_color)) },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (cover.isNotEmpty() && !isValidImageUrl(cover)) {
                        Text(
                            "La URL debe ser una imagen válida (extensiones permitidas: jpg, jpeg, png, gif)",
                            modifier = Modifier.padding(top = 4.dp),
                            color = androidx.compose.ui.graphics.Color.Red
                        )
                    }
                    if (coverError) {
                        Text(text = "Campo imagen es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    OutlinedTextField(
                        value = recordLabel,
                        onValueChange = {recordLabel = it},
                        label = { Text(text = "Compañia discografica", color = colorResource(id = R.color.label_text_color)) },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    hideKeyBoard(view)
                                }
                            }
                    )
                    if (recordLabelError) {
                        Text(text = "Campo Compañia es obligatorio", color = Color.Red, fontSize = 12.sp)
                    }
                    CustomDatePicker(
                        value = releaseDate.value,
                        onValueChange = {releaseDate.value = it}
                    )
                    Button(
                        onClick = {

                            validateFields()
                            if (!nameError && !descriptionError && !genreError && !coverError && !recordLabelError) {
                                formData.value = AlbumFormData(name, description, recordLabel , genre,cover, releaseDate.value.format(
                                    DateTimeFormatter.ISO_DATE))
                                sendData(formData.value, viewModel)
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


fun sendData(formData: AlbumFormData, viewModel: AlbumCreateViewModel ) {
    formData.toJsonObject()
    viewModel.createAlbum(formData.toJsonObject())
}




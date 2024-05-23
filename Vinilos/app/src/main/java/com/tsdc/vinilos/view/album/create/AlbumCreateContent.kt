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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.google.gson.JsonObject
import com.tsdc.vinilos.R
import com.tsdc.vinilos.presentation.album.AlbumCreateViewModel
import com.tsdc.vinilos.ui.theme.VinilosTheme

import com.tsdc.vinilos.view.utils.CustomDatePicker
import com.tsdc.vinilos.view.utils.isValidImageUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.core.content.ContextCompat
import com.tsdc.vinilos.data.model.RecordLabel
import com.tsdc.vinilos.data.model.Recurrence
import com.tsdc.vinilos.view.collector.login.CollectorMenuActivity

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
fun AlbumCreateContent(
    paddingValues: PaddingValues,
    scrollState: ScrollState,
    viewModel: AlbumCreateViewModel
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var recordLabel by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var cover by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var genreError by remember { mutableStateOf(false) }
    var coverError by remember { mutableStateOf(false) }
    var recordLabelError by remember { mutableStateOf(false) }

    val releaseDate = remember { mutableStateOf(LocalDate.now()) }
    val localContext = LocalContext.current

    fun validateFields() {
        nameError = name.isBlank()
        descriptionError = description.isBlank()
        genreError = genre.isBlank()
        coverError = cover.isBlank()
        recordLabelError = recordLabel.isBlank()
    }

    val formData = remember { mutableStateOf(AlbumFormData("", "", "", "", "", "")) }
    val coroutineScope = rememberCoroutineScope()
    var recurrence by rememberSaveable { mutableStateOf(Recurrence.Classical.name) }
    var recordLabelEnum by rememberSaveable { mutableStateOf(RecordLabel.FaniaRecords.name) }

    BoxWithConstraints(
        Modifier
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
                        label = {
                            Text(
                                text = "Nombre",
                                color = colorResource(id = R.color.label_text_color)
                            )
                        },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (nameError) {
                        Text(
                            text = "Campo nombre es obligatorio",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = {
                            Text(
                                text = "Descripción",
                                color = colorResource(id = R.color.label_text_color)
                            )
                        },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (descriptionError) {
                        Text(
                            text = "Campo descripción es obligatorio",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    OutlinedTextField(
                        value = cover,
                        onValueChange = { cover = it },
                        label = {
                            Text(
                                text = "Imagen",
                                color = colorResource(id = R.color.label_text_color)
                            )
                        },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    if (cover.isNotEmpty() && !isValidImageUrl(cover)) {
                        Text(
                            "La URL debe empezar con https:// una imagen válida (extensiones permitidas: jpg, jpeg, png, gif)",
                            modifier = Modifier.padding(top = 4.dp),
                            color = androidx.compose.ui.graphics.Color.Red
                        )
                    }
                    if (coverError) {
                        Text(
                            text = "Campo imagen es obligatorio",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    RecordLabelDropdownMenu{
                        recordLabelEnum = it
                        recordLabel = recordLabelEnum
                    }
                    if (recordLabelError) {
                        Text(
                            text = "Campo compañia es obligatorio",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    CustomDatePicker(
                        value = releaseDate.value,
                        onValueChange = { releaseDate.value = it }
                    )
                    RecurrenceDropdownMenu {
                        recurrence = it
                        genre = recurrence
                    }
                    if (genreError) {
                        Text(
                            text = "Campo género es obligatorio",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    Button(
                            onClick = {
                                validateFields()
                                if (!nameError && !descriptionError && !genreError && !coverError && !recordLabelError) {
                                    formData.value = AlbumFormData(
                                        name,
                                        description,
                                        recordLabel,
                                        genre,
                                        cover,
                                        releaseDate.value.format(
                                            DateTimeFormatter.ISO_DATE
                                        )
                                    )
                                    coroutineScope.launch(Dispatchers.IO) {
                                        sendData(formData.value, viewModel)
                                    }
                                    ContextCompat.startActivity(
                                        localContext,
                                        Intent(localContext, CollectorMenuActivity::class.java),
                                        null
                                    )
                                }
                            },
                            modifier = Modifier.padding(top = 20.dp)
                        ) {
                            Text(text = "Registrar álbum")
                        }
                    }
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecurrenceDropdownMenu(recurrence: (String) -> Unit) {
    Column{
        val options = getRecurrenceList().map { it.name }
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                modifier = Modifier
                .menuAnchor()
                .testTag("recurrenceDropdownMenu")
                .padding(top = 20.dp),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        modifier = Modifier.testTag("recurrenceDropdownItem"),
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            recurrence(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordLabelDropdownMenu(recordLabelEnum: (String) -> Unit) {
    Column{
        val options = getRecordLabelList()
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                modifier = Modifier
                .menuAnchor()
                .testTag("recordLabelDropdownMenu")
                .padding(top = 20.dp),

            readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        modifier = Modifier.testTag("recordLabelDropdownItem"),
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            recordLabelEnum(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

suspend fun sendData(formData: AlbumFormData, viewModel: AlbumCreateViewModel ) {
    formData.toJsonObject()
    viewModel.createAlbum(formData.toJsonObject())
}

fun getRecurrenceList(): List<Recurrence> {
    return Recurrence.entries
}

fun getRecordLabelList(): List<String> {
    return RecordLabel.values().map { it -> it.value }
}

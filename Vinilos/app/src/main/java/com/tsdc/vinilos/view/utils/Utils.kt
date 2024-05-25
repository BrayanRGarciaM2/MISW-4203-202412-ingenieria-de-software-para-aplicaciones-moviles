package com.tsdc.vinilos.view.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.tsdc.vinilos.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ActionItems() {
    val activity = LocalContext.current as? ComponentActivity
    // IconButton se beneficia del RowScope para ser colocado dentro de un Row
    IconButton(
        onClick = { activity?.finish() },
        modifier = Modifier.testTag("BackButton")
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = ContextCompat.getString(
                LocalContext.current,
                R.string.back_button_text
            ),
            modifier = Modifier
                .size(60.dp),
            tint = Color.White
        )
    }
}

@Composable
fun isValidImageUrl(url: String): Boolean {
    return url.matches(Regex("""^(https?://).*\.(?:png|jpe?g|gif)$"""))
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit
) {

    val open = remember { mutableStateOf(false) }

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

fun hideKeyBoard(view: View){
    val context = view.context
    val imn = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imn.hideSoftInputFromWindow(view.windowToken, 0)
}


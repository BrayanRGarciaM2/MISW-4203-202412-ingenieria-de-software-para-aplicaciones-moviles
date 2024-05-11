package com.tsdc.vinilos.view.utils

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.tsdc.vinilos.R

@Composable
fun ActionItems() {
    val activity = LocalContext.current as? ComponentActivity
    // IconButton se beneficia del RowScope para ser colocado dentro de un Row
    IconButton(onClick = { activity?.finish() }) {
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
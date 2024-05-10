package com.tsdc.vinilos.view.artist.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.tsdc.vinilos.R
import com.tsdc.vinilos.view.album.detail.ActionItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailToolbar() {
    val colorResource = colorResource(id = R.color.background_toolbar)
    val toolbarColor = remember { mutableStateOf(colorResource) }
    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = ContextCompat.getString(LocalContext.current, R.string.artist_text),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.testTag("ArtistDetailTitle")
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(toolbarColor.value),
        navigationIcon = { ActionItems() })
}

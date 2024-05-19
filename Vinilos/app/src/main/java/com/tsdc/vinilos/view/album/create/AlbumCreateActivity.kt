package com.tsdc.vinilos.view.album.create

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.tsdc.vinilos.ui.theme.VinilosTheme

class AlbumCreateActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
                AlbumCreateScreen()
            }
        }
    }
}
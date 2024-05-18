package com.tsdc.vinilos.view.album.create

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tsdc.vinilos.ui.theme.VinilosTheme

class AlbumCreateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
                AlbumCreateScreen()
            }
        }
    }
}
package com.tsdc.vinilos.view.collector.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tsdc.vinilos.data.remote.RemoteCollectorDataSource
import com.tsdc.vinilos.presentation.collector.CollectorListViewModelFactory
import com.tsdc.vinilos.presentation.collector.CollectorListViewModel
import com.tsdc.vinilos.repository.collector.CollectorRepositoryImpl
import com.tsdc.vinilos.ui.theme.VinilosTheme

class CollectorListActivity : ComponentActivity() {

    val viewModel by viewModels<CollectorListViewModel> {
        CollectorListViewModelFactory(
            CollectorRepositoryImpl(
                RemoteCollectorDataSource()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    CollectorListScreen(viewModel)
                }
            }
        }
    }
}
package com.tsdc.vinilos.view.collector.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.data.remote.collector.detail.RemoteCollectorDetailDataSource
import com.tsdc.vinilos.presentation.collector.detail.CollectorDetailViewModel
import com.tsdc.vinilos.presentation.collector.detail.CollectorDetailViewModelFactory
import com.tsdc.vinilos.repository.collector.detail.CollectorDetailRepositoryImpl
import com.tsdc.vinilos.ui.theme.VinilosTheme


class CollectorDetailActivity : ComponentActivity() {

    val viewModel by viewModels<CollectorDetailViewModel> {
        CollectorDetailViewModelFactory(
            CollectorDetailRepositoryImpl(
                RemoteCollectorDetailDataSource()
            )
        )
    }

    @Suppress("DEPRECATION")
    private val collector: Collector? by lazy {
        intent?.getParcelableExtra(COLLECTOR_ID) as? Collector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CollectorDetailScreen(collector, viewModel )
                }
            }
        }
    }

    companion object {
        private const val COLLECTOR_ID = "collector_id"
        fun newIntent(context: Context, collector: Collector): Intent =
            Intent(context, CollectorDetailActivity::class.java).apply {
                putExtra(COLLECTOR_ID, collector)
            }
    }
}

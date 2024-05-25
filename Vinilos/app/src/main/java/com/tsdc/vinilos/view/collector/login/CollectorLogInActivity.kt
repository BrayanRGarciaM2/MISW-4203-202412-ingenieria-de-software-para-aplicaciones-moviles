package com.tsdc.vinilos.view.collector.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tsdc.vinilos.R
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.local.login.LocalLoginDataSource
import com.tsdc.vinilos.data.remote.login.RemoteLoginDataSource
import com.tsdc.vinilos.presentation.collector.login.CollectorLoginViewModel
import com.tsdc.vinilos.presentation.collector.login.CollectorLoginViewModelFactory
import com.tsdc.vinilos.repository.login.LoginRepositoryImpl
import com.tsdc.vinilos.ui.theme.VinilosTheme

class CollectorLogInActivity : ComponentActivity() {

    val viewModel by viewModels<CollectorLoginViewModel> {
        CollectorLoginViewModelFactory(
            LoginRepositoryImpl(
                LocalLoginDataSource(this),
                RemoteLoginDataSource()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitLoginActivity()

        }
    }

    fun String.isEmailValid(): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return emailRegex.toRegex().matches(this)
    }

    @Preview(showBackground = true)
    @Composable
    fun InitLoginActivity() {
        val localContext = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        var collectorUser by remember {
            mutableStateOf("")
        }
        var isError by remember {
            mutableStateOf(false)
        }

        VinilosTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LazyColumn {
                    item {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(top = 97.dp),
                            painter = painterResource(id = R.drawable.vinilos_icon),
                            contentDescription = "Icono de Vinilos"
                        )
                    }
                    item {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextField(
                                    value = collectorUser,
                                    onValueChange = { collectorUser = it },
                                    label = { Text(text = "Digite su correo") },
                                    //isError = isError,
                                    supportingText = {
                                        if (isError) {
                                            if (collectorUser == "") {
                                                Text(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    text = "Digite su correo",
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                            if (!collectorUser.isEmailValid() && collectorUser != "") {
                                                Text(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    text = "Dígite un correo valido",
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }

                                    },
                                )
                                Button(
                                    onClick = {
                                        isError = false

                                        if (collectorUser.isEmailValid() && collectorUser != "") {
                                            viewModel.checkEmail(collectorUser)
                                                .observe(lifecycleOwner) { result ->
                                                    when (result) {
                                                        is Output.Loading -> {}
                                                        is Output.Failure<*> -> {}
                                                        is Output.Success -> {
                                                            result.data?.let {
                                                                viewModel.saveCollectorId(result.data.id)
                                                                localContext.startActivity(
                                                                    Intent(
                                                                        localContext,
                                                                        CollectorMenuActivity::class.java
                                                                    )
                                                                )
                                                            } ?: Toast.makeText(
                                                                localContext,
                                                                "Correo no encontrado",
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                        }
                                                    }
                                                }


                                        } else {
                                            isError = true
                                        }
                                    },
                                    modifier = Modifier.padding(top = 20.dp)
                                ) {
                                    Text(text = "Identificarse")
                                }
                            }

                        }

                    }
                }
            }
        }
    }
}


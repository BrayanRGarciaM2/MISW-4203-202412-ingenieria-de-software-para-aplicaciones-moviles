package com.tsdc.vinilos.presentation.collector.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers

class CollectorLoginViewModel(private val repo: LoginRepository): ViewModel() {
    fun saveCollectorId(id:Int) {
        repo.saveCollectorId(id)
    }

    fun checkEmail(email:String) = liveData (Dispatchers.IO) {
        emit(Output.Loading())
        try {
            val collectors: List<Collector> = repo.getCollectors()
            val collector = collectors.find { it.email == "manollo@caracol.com.co" }
            emit(Output.Success(collector))
        } catch (e: Exception) {
            emit(Output.Failure(e))
        }
    }
}

class CollectorLoginViewModelFactory(private val repo: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(LoginRepository::class.java).newInstance(repo)
}

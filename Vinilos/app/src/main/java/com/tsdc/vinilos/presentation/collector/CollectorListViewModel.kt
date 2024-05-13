package com.tsdc.vinilos.presentation.collector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.repository.collector.CollectorRepository
import kotlinx.coroutines.Dispatchers

class CollectorListViewModel(private val repo: CollectorRepository) : ViewModel() {

    fun getCollectors() = liveData(Dispatchers.IO) {
        emit(Output.Loading())
        try {
            emit(Output.Success(repo.getCollectors()))
        } catch (e: Exception) {
            emit(Output.Failure<Exception>(e))
        }
    }
}
class CollectorListViewModelFactory(private val repo: CollectorRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(CollectorRepository::class.java).newInstance(repo)

}

package com.tsdc.vinilos.presentation.collector.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.repository.collector.detail.CollectorDetailRepository
import kotlinx.coroutines.Dispatchers

class CollectorDetailViewModel (private val repo: CollectorDetailRepository) : ViewModel() {

    fun getAlbumById(albumId : Int) = liveData(Dispatchers.IO) {
        emit(Output.Loading())
        try {
            emit(Output.Success(repo.getAlbumById(albumId)))
        } catch (e: Exception) {
            emit(Output.Failure<Exception>(e))
        }
    }
}
class CollectorDetailViewModelFactory(private val repo: CollectorDetailRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(CollectorDetailRepository::class.java).newInstance(repo)

}
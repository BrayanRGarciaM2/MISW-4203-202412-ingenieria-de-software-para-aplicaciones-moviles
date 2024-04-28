package com.tsdc.vinilos.album.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tsdc.vinilos.album.repository.AlbumRepository
import com.tsdc.vinilos.core.Output
import kotlinx.coroutines.Dispatchers

class AlbumListViewModel(private val repo: AlbumRepository) : ViewModel() {
    fun getAlbums() = liveData(Dispatchers.IO) {
        emit(Output.Loading())
        try {
            emit(Output.Success(repo.getAlbums()))
        } catch (e: Exception) {
            emit(Output.Failure<Exception>(e))
        }
    }
}

class AlbumListViewModelFactory(private val repo: AlbumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(AlbumRepository::class.java).newInstance(repo)

}
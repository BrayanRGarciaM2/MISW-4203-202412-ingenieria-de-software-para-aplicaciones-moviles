package com.tsdc.vinilos.presentation.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.repository.album.AlbumRepository
import kotlinx.coroutines.Dispatchers

class AlbumListViewModel(private val repo: AlbumRepository) : ViewModel() {
    fun getAlbums() = liveData(Dispatchers.IO) {
        emit(Output.Loading())
        try {
            emit(Output.Success(data = repo.getAlbums()))
        } catch (e: Exception) {
            emit(Output.Failure(exception = e))
        }
    }
}

class AlbumListViewModelFactory(private val repo: AlbumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(AlbumRepository::class.java).newInstance(repo)

}
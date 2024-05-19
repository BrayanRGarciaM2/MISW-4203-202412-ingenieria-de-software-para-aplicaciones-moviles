package com.tsdc.vinilos.presentation.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.google.gson.JsonObject
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.repository.album.AlbumRepository
import kotlinx.coroutines.Dispatchers

class AlbumCreateViewModel(private val repo: AlbumRepository) : ViewModel() {
       fun createAlbum(album: JsonObject) = liveData(Dispatchers.IO) {
        emit(Output.Loading())
        try {
            emit(Output.Success(data = repo.createAlbum(album)))
        } catch (e: Exception) {
            emit(Output.Failure(exception = e))
        }
    }
}

class AlbumCreateViewModelFactory(private val repo: AlbumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(AlbumRepository::class.java).newInstance(repo)

}
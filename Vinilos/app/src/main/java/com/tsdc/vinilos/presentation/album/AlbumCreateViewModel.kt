package com.tsdc.vinilos.presentation.album

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.tsdc.vinilos.repository.album.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumCreateViewModel(private val repo: AlbumRepository) : ViewModel() {
    suspend fun createAlbum(album: JsonObject) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.createAlbum(album)
            } catch (e: Exception) {
                e.message?.let { Log.e("Create Album", it) }
            }
        }
    }
}

class AlbumCreateViewModelFactory(private val repo: AlbumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(AlbumRepository::class.java).newInstance(repo)

}
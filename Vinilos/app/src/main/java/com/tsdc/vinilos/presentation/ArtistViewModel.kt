package com.tsdc.vinilos.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsdc.vinilos.data.APIService
import com.tsdc.vinilos.data.Artist
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {
    private val _artistList = mutableStateListOf<Artist>()
    var errorMessage: String by mutableStateOf("")
    val artistList: List<Artist>
        get() = _artistList

    fun getArtistList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _artistList.clear()
                _artistList.addAll(apiService.getArtists())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

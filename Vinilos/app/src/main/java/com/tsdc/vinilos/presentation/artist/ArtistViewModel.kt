package com.tsdc.vinilos.presentation.artist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.repository.artist.ArtistRepository
import kotlinx.coroutines.Dispatchers
class ArtistViewModel(private val repo: ArtistRepository) : ViewModel() {
    fun getArtists() = liveData(Dispatchers.IO) {
        emit(Output.Loading())
        try {
            emit(Output.Success(repo.getArtists()))
        } catch (e: Exception) {
            emit(Output.Failure<Exception>(e))
        }
    }
}

class ArtistListViewModelFactory(private val repo: ArtistRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(ArtistRepository::class.java).newInstance(repo)

}
package com.tsdc.vinilos.presentation.performer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tsdc.vinilos.core.Output
import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.repository.artist.ArtistRepository
import com.tsdc.vinilos.repository.login.LoginRepository
import com.tsdc.vinilos.repository.performer.PerformersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritePerformerViewModel(
    private val artistRepo: ArtistRepository,
    private val performersRepo: PerformersRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    fun getPerformerList() = liveData(Dispatchers.IO) {
        emit(Output.Loading())
        try {
            emit(Output.Success(artistRepo.getArtists()))
        } catch (e: Exception) {
            emit(Output.Failure(e))
        }
    }

    fun addFavoritePerformers(favoritePerformers: List<Artist?>) {
        viewModelScope.launch(Dispatchers.IO) {
            val collectorId = loginRepository.getCollectorId()
            performersRepo.addFavoritePerformers(favoritePerformers, collectorId)
        }
    }
}

class FavoritePerformerViewModelFactory(
    private val repo: ArtistRepository,
    private val performersRepo: PerformersRepository,
    private val loginRepository: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            ArtistRepository::class.java,
            PerformersRepository::class.java,
            LoginRepository::class.java
        ).newInstance(repo, performersRepo, loginRepository)

}

package com.tsdc.vinilos.data.remote.performer

import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.repository.core.RetrofitClient
import com.tsdc.vinilos.repository.performer.PerformersService

class PerformerRemoteDataSource {
    private val remoteApiSource = RetrofitClient.createWebService<PerformersService>()

    suspend fun addFavoritePerformers(favoritePerformers: List<Artist?>, collectorId: Int) {
        favoritePerformers.forEach { artist ->
            artist?.let {
                remoteApiSource.addFavoritePerformer(collectorId, artist.id)
            }
        }
    }
}

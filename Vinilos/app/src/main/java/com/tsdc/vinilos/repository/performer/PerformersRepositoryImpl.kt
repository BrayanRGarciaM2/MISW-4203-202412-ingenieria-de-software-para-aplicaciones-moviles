package com.tsdc.vinilos.repository.performer

import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.data.remote.performer.PerformerRemoteDataSource

class PerformersRepositoryImpl(
    private val performerRemoteDataSource: PerformerRemoteDataSource
) : PerformersRepository {
    override suspend fun addFavoritePerformers(
        favoritePerformers: List<Artist?>,
        collectorId: Int
    ) {
        performerRemoteDataSource.addFavoritePerformers(favoritePerformers, collectorId)
    }
}

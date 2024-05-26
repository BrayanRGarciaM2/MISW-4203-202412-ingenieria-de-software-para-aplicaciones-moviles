package com.tsdc.vinilos.repository.performer

import com.tsdc.vinilos.data.model.Artist

interface PerformersRepository {
    suspend fun addFavoritePerformers(favoritePerformers: List<Artist?>, collectorId: Int)
}

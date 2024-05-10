package com.tsdc.vinilos.data.remote.artist

import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.repository.artist.ArtistService
import com.tsdc.vinilos.repository.core.RetrofitClient

class ArtistRemoteDataSource {
    private val apiService = RetrofitClient.createWebService<ArtistService>()
    suspend fun getArtists(): List<Artist> = apiService.getArtists()
}
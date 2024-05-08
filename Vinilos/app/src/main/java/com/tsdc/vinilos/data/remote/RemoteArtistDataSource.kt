package com.tsdc.vinilos.data.remote

import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.repository.artist.ArtistRetrofitClient

class RemoteArtistDataSource {
    suspend fun getArtists(): List<Artist>{
        return ArtistRetrofitClient.webService.getArtists()
    }
}
package com.tsdc.vinilos.repository.artist

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.Artist
import retrofit2.http.GET

interface ArtistService {
    @GET("musicians")
    suspend fun getArtists(): List<Artist>
}
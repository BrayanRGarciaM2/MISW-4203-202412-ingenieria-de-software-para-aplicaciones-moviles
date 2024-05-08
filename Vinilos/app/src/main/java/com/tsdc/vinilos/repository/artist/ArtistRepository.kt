package com.tsdc.vinilos.repository.artist

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>

}
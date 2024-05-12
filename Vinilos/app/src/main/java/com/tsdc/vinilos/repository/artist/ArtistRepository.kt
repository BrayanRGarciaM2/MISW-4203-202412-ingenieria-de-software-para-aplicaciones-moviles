package com.tsdc.vinilos.repository.artist

import com.tsdc.vinilos.data.model.ArtistList

interface ArtistRepository {
    suspend fun getArtists(): ArtistList
}
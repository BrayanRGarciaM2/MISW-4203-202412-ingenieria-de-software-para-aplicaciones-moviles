package com.tsdc.vinilos.repository.artist

import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.data.remote.artist.ArtistRemoteDataSource

class ArtistRepositoryImpl(private val artistRemoteDataSource: ArtistRemoteDataSource): ArtistRepository {
    override suspend fun getArtists(): List<Artist> = artistRemoteDataSource.getArtists()
}
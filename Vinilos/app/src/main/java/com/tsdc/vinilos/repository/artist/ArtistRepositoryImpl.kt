package com.tsdc.vinilos.repository.artist

import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.data.remote.RemoteArtistDataSource

class ArtistRepositoryImpl(private val dataSourceRemote: RemoteArtistDataSource) : ArtistRepository {
    override suspend fun getArtists(): List<Artist> = dataSourceRemote.getArtists()
}
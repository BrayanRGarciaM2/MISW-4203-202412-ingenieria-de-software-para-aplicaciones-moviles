package com.tsdc.vinilos.data.local.artista

import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.data.model.ArtistList
import com.tsdc.vinilos.data.model.toArtistEntity
import com.tsdc.vinilos.data.model.toArtistList

class LocalArtistDataSource(private val artistDao: IArtistDao) {

    suspend fun getArtists(): ArtistList = artistDao.getAllArtists().toArtistList()

    suspend fun saveArtists(artists: List<Artist>) {
        artists.forEach { artist ->
            artistDao.saveArtist(artist.toArtistEntity())
        }
    }

}
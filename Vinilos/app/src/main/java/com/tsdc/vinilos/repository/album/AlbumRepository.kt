package com.tsdc.vinilos.repository.album

import com.tsdc.vinilos.data.model.Album

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
}
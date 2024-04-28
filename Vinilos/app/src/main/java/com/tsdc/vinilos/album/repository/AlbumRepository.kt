package com.tsdc.vinilos.album.repository

import com.tsdc.vinilos.album.data.model.Album

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
}
package com.tsdc.vinilos.repository.album

import com.google.gson.JsonObject
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.AlbumList

interface AlbumRepository {
    suspend fun getAlbums(): AlbumList

    suspend fun createAlbum(album: JsonObject)

    suspend fun getAlbumById(albumId: Int): Album?
}

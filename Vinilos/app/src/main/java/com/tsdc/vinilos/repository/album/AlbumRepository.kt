package com.tsdc.vinilos.repository.album

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.AlbumList

interface AlbumRepository {
    suspend fun getAlbums(): AlbumList
    suspend fun getAlbumById(albumId: Int): Album?
}

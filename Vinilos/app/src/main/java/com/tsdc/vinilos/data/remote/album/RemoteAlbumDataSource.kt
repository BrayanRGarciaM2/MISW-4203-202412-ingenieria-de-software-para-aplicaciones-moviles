package com.tsdc.vinilos.data.remote.album

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.repository.album.AlbumService
import com.tsdc.vinilos.repository.core.RetrofitClient

class RemoteAlbumDataSource {
    private val remoteApiSource = RetrofitClient.createWebService<AlbumService>()
    suspend fun getAlbums(): List<Album> {
        return remoteApiSource.getAlbums()
    }

    suspend fun getAlbumById(albumId: Int): Album? {
        return remoteApiSource.getAlbumById(albumId)
    }
}

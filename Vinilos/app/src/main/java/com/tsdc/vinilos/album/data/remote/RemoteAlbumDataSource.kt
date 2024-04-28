package com.tsdc.vinilos.album.data.remote

import com.tsdc.vinilos.album.data.model.Album
import com.tsdc.vinilos.album.repository.AlbumRetrofitClient

class RemoteAlbumDataSource {
    suspend fun getAlbums(): List<Album> {
        return AlbumRetrofitClient.webService.getAlbums()
    }
}
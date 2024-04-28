package com.tsdc.vinilos.data.remote

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.repository.album.AlbumRetrofitClient

class RemoteAlbumDataSource {
    suspend fun getAlbums(): List<Album> {
        return AlbumRetrofitClient.webService.getAlbums()
    }
}
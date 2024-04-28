package com.tsdc.vinilos.album.repository

import com.tsdc.vinilos.album.data.model.Album
import com.tsdc.vinilos.album.data.remote.RemoteAlbumDataSource

class AlbumRepositoryImpl(private val dataSourceRemote: RemoteAlbumDataSource) : AlbumRepository {
    override suspend fun getAlbums(): List<Album> = dataSourceRemote.getAlbums()
}
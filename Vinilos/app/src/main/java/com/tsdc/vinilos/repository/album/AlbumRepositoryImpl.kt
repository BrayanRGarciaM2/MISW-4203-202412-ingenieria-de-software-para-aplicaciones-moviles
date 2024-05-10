package com.tsdc.vinilos.repository.album

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.remote.album.RemoteAlbumDataSource

class AlbumRepositoryImpl(private val dataSourceRemote: RemoteAlbumDataSource) : AlbumRepository {
    override suspend fun getAlbums(): List<Album> = dataSourceRemote.getAlbums()
}
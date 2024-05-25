package com.tsdc.vinilos.repository.collector.detail

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.remote.collector.detail.RemoteCollectorDetailDataSource

class CollectorDetailRepositoryImpl(private val dataSourceRemote: RemoteCollectorDetailDataSource) : CollectorDetailRepository {
    override suspend fun getAlbumById(albumId: Int): Album? {
        return dataSourceRemote.getAlbumById(albumId)
    }
}
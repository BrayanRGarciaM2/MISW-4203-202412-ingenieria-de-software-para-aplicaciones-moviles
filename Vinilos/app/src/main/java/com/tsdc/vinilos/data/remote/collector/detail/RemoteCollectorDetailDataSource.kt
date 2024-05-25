package com.tsdc.vinilos.data.remote.collector.detail

import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.repository.collector.detail.CollectorDetailService
import com.tsdc.vinilos.repository.core.RetrofitClient

class RemoteCollectorDetailDataSource {

    private val remoteApiSource = RetrofitClient.createWebService<CollectorDetailService>()

    suspend fun getAlbumById(albumId: Int): Album? {
        return remoteApiSource.getAlbumById(albumId)
    }
}
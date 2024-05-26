package com.tsdc.vinilos.data.remote.login

import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.repository.collector.CollectorService
import com.tsdc.vinilos.repository.core.RetrofitClient

class RemoteLoginDataSource {
    private val remoteApiSource = RetrofitClient.createWebService<CollectorService>()

    suspend fun getCollectors(): List<Collector> {
        return remoteApiSource.getCollectors()
    }
}
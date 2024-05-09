package com.tsdc.vinilos.data.remote

import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.repository.collector.CollectorRetrofitClient

class RemoteCollectorDataSource {
    suspend fun getCollectors(): List<Collector> {
        return CollectorRetrofitClient.webService.getCollectors()
    }
}
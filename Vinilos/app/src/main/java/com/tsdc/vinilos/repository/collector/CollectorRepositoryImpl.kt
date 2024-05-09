package com.tsdc.vinilos.repository.collector

import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.data.remote.RemoteCollectorDataSource

class CollectorRepositoryImpl(private val dataSourceRemote: RemoteCollectorDataSource) : CollectorRepository {
    override suspend fun getCollectors(): List<Collector> = dataSourceRemote.getCollectors()
}
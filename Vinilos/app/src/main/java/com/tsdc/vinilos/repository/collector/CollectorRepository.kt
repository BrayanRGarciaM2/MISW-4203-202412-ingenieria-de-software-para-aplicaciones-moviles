package com.tsdc.vinilos.repository.collector

import com.tsdc.vinilos.data.model.Collector

interface CollectorRepository {
    suspend fun getCollectors(): List<Collector>
}
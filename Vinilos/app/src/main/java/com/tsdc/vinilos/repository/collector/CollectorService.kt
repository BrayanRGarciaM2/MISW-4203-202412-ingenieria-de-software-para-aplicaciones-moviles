package com.tsdc.vinilos.repository.collector

import com.tsdc.vinilos.data.model.Collector
import retrofit2.http.GET

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<Collector>
}
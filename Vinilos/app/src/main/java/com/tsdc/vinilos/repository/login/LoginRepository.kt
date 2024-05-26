package com.tsdc.vinilos.repository.login

import com.tsdc.vinilos.data.model.Collector

interface LoginRepository {
    fun saveCollectorId(id:Int)
    fun getCollectorId():Int

    suspend fun getCollectors():List<Collector>

}
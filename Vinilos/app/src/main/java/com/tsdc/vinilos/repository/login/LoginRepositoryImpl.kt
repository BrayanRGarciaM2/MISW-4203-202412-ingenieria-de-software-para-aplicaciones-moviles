package com.tsdc.vinilos.repository.login

import com.tsdc.vinilos.data.local.login.LocalLoginDataSource
import com.tsdc.vinilos.data.model.Collector
import com.tsdc.vinilos.data.remote.login.RemoteLoginDataSource

class LoginRepositoryImpl(private val dataSourceLocal: LocalLoginDataSource, private val dataSourceRemote: RemoteLoginDataSource) : LoginRepository {
    override fun saveCollectorId(id:Int) = dataSourceLocal.saveCollectorId(id)
    override fun getCollectorId():Int = dataSourceLocal.getCollectorId()
    override suspend fun getCollectors():List<Collector> = dataSourceRemote.getCollectors()

}
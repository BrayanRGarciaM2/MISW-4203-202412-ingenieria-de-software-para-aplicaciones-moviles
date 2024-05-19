package com.tsdc.vinilos.repository.album

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.JsonObject
import com.tsdc.vinilos.data.local.album.LocalAlbumDataSource
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.AlbumList
import com.tsdc.vinilos.data.remote.album.RemoteAlbumDataSource

class AlbumRepositoryImpl(
    private val application: Application,
    private val dataSourceRemote: RemoteAlbumDataSource,
    private val datasourceLocal: LocalAlbumDataSource
) : AlbumRepository {
    override suspend fun getAlbums(): AlbumList {
        val cachedAlbums = datasourceLocal.getAlbums()

        if (cachedAlbums.results.isEmpty()) {
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (
                cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI &&
                cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE
            ) {
                return AlbumList()
            } else
                saveAlbums(dataSourceRemote.getAlbums())
        }

        return datasourceLocal.getAlbums()
    }

    override suspend fun createAlbum(album: JsonObject): Album {
        return dataSourceRemote.createAlbum(album)
    }

    private suspend fun saveAlbums(albums: List<Album>) {
        datasourceLocal.saveAlbums(albums)
    }
}

package com.tsdc.vinilos.data.local.album

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.AlbumEntity
import com.tsdc.vinilos.data.model.AlbumList
import com.tsdc.vinilos.data.model.toAlbumEntity
import com.tsdc.vinilos.data.model.toAlbumList

class LocalAlbumDataSource(private val albumDao: IAlbumDao) {

    suspend fun getAlbums(): AlbumList = albumDao.getAllAlbums().toAlbumList()

    suspend fun saveAlbums(albums: List<Album>) {
        albums.forEach { album ->
            albumDao.saveAlbum(album.toAlbumEntity())
        }
    }

    suspend fun createAlbum(album: Album) {
        albumDao.createAlbum(album.toAlbumEntity())
    }
}

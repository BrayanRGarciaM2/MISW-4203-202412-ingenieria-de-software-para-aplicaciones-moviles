package com.tsdc.vinilos.album.repository

import com.tsdc.vinilos.album.data.model.Album
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}
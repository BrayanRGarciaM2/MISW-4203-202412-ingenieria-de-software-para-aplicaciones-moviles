package com.tsdc.vinilos.repository.album

import com.tsdc.vinilos.data.model.Album
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}
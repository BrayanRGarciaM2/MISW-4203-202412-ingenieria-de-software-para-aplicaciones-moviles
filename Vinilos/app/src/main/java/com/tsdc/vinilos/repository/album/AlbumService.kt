package com.tsdc.vinilos.repository.album

import com.google.gson.JsonObject
import com.tsdc.vinilos.data.model.Album
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @POST("albums")
    suspend fun createAlbum(@Body album: JsonObject): Album
}
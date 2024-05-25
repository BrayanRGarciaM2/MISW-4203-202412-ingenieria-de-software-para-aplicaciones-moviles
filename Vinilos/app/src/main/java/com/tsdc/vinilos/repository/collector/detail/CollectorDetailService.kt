package com.tsdc.vinilos.repository.collector.detail

import com.tsdc.vinilos.data.model.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorDetailService {

    @GET("albums/{albumId}")
    suspend fun getAlbumById(@Path("albumId") albumId: Int): Album?
}
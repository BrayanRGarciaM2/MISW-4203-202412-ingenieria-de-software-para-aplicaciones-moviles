package com.tsdc.vinilos.repository.album

import com.google.gson.GsonBuilder
import com.tsdc.vinilos.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumRetrofitClient {

    val webService by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(AlbumService::class.java)
    }
}
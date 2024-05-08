package com.tsdc.vinilos.repository.artist

import com.google.gson.GsonBuilder
import com.tsdc.vinilos.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArtistRetrofitClient {

    val webService by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(ArtistService::class.java)
    }
}
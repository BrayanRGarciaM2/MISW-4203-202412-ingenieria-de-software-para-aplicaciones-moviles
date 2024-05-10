package com.tsdc.vinilos.repository.core

import com.google.gson.GsonBuilder
import com.tsdc.vinilos.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    inline fun <reified T> createWebService(): T {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(T::class.java)
    }
}
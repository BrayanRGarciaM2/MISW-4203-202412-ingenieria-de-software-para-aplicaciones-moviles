package com.tsdc.vinilos.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Artist(
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<Album>,
    val performerPrizes: List<PerformerPrize>
)

data class Album (
    val id: Long,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)

data class PerformerPrize (
    val id: Long,
    val premiationDate: String
)

const val BASE_URL = "http://157.253.198.23:3000/"

interface APIService {
    @GET("musicians")
    suspend fun getArtists(): List<Artist>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}
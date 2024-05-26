package com.tsdc.vinilos.repository.performer

import com.tsdc.vinilos.data.model.Performer
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface PerformersService {
    @POST("collectors/{id_collector}/musicians/{id_musician}")
    suspend fun addFavoritePerformer(
        @Path("id_collector") idCollector: Int,
        @Path("id_musician") idMusician: Int
    ): Response<Performer>
}

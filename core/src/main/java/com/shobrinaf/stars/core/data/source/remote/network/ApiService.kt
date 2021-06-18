package com.shobrinaf.stars.core.data.source.remote.network

import com.shobrinaf.stars.core.data.source.remote.response.StarResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/?start_date=2021-04-01&end_date=2021-06-04")
    suspend fun getList(): List<StarResponse>

    @GET("search/")
    suspend fun getSearch(
        @Query("search_query") search_query: String
    ): List<StarResponse>
}

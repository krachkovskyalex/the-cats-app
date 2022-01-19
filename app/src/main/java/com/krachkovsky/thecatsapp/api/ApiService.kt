package com.krachkovsky.thecatsapp.api

import com.krachkovsky.thecatsapp.models.CatsListItem
import com.krachkovsky.thecatsapp.util.Constants.QUERY_PARAM_LIMIT
import com.krachkovsky.thecatsapp.util.Constants.QUERY_PARAM_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getCatsList(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_PAGE) page: Int = 0,
        ): Response<List<CatsListItem>>
}
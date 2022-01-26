package com.krachkovsky.thecatsapp.api

import com.krachkovsky.thecatsapp.models.AnyCat
import com.krachkovsky.thecatsapp.util.Constants.PARAM_ORDER_RANDOM
import com.krachkovsky.thecatsapp.util.Constants.PARAM_PAGE_LIMIT
import com.krachkovsky.thecatsapp.util.Constants.QUERY_PARAM_LIMIT
import com.krachkovsky.thecatsapp.util.Constants.QUERY_PARAM_ORDER
import com.krachkovsky.thecatsapp.util.Constants.QUERY_PARAM_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getCatsList(
        @Query(QUERY_PARAM_ORDER) order: String = PARAM_ORDER_RANDOM,
        @Query(QUERY_PARAM_LIMIT) limit: Int = PARAM_PAGE_LIMIT,
        @Query(QUERY_PARAM_PAGE) page: Int = 0,
    ): Response<List<AnyCat>>
}
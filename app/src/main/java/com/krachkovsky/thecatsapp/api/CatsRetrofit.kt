package com.krachkovsky.thecatsapp.api

import com.krachkovsky.thecatsapp.util.Constants.API_KEY
import com.krachkovsky.thecatsapp.util.Constants.API_KEY_NAME
import com.krachkovsky.thecatsapp.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatsRetrofit {

    fun apiRequest(): ApiService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header(name = API_KEY_NAME, value = API_KEY)
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
package com.ld5ehom.movie.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ld5ehom.movie.common.extension.gson
import com.ld5ehom.movie.remote.api.interceptor.RequestHeaderInterceptor
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30

fun createApiService(baseUrl: String): ApiService {
    val okHttpClient = OkHttpClient.Builder().apply {
        readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

        addNetworkInterceptor(RequestHeaderInterceptor())

    }.build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)

}


package com.ld5ehom.movie.remote.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import com.ld5ehom.movie.common.exception.ErrorUtil
import com.ld5ehom.movie.remote.BuildConfig
import java.io.IOException

internal class RequestHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = try {
            chain.request().newBuilder()
                .apply {
                    addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                }
                .build()
        } catch (exception: Exception) {
            ErrorUtil.handleError(exception)
            chain.request()
        }

        return chain.proceed(newRequest)
    }

}

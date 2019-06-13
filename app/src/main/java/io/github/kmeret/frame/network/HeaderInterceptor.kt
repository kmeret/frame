package io.github.kmeret.frame.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val key: String, private val value: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithHeaderBuilder = chain.request().newBuilder()
        requestWithHeaderBuilder.addHeader(key, value)
        return chain.proceed(requestWithHeaderBuilder.build())
    }
}
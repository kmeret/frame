package io.github.kmeret.frame.infrastructure.data.network

import io.github.kmeret.frame.infrastructure.application.BaseApp
import io.github.kmeret.frame.infrastructure.data.network.interceptors.ErrorInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object HttpClientFactory {

    fun okHttpClient(builder: OkHttpClient.Builder.() -> Unit = {}): OkHttpClient {
        return clientBuilder()
                .apply {
                    builder(this)
                    addInterceptor(getUserAgentInterceptor())
                    addInterceptor(getLoggingInterceptor())
                    addInterceptor(ErrorInterceptor())
                }
                .build()
    }

    private fun clientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(BaseApp.HTTP_CLIENT_TIMEOUT_SEC, TimeUnit.SECONDS)
            readTimeout(BaseApp.HTTP_CLIENT_TIMEOUT_SEC, TimeUnit.SECONDS)
            writeTimeout(BaseApp.HTTP_CLIENT_TIMEOUT_SEC, TimeUnit.SECONDS)
        }
    }

    private fun getUserAgentInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestWithUserAgent = originalRequest.newBuilder()
                .header("User-Agent",  "Android")
                .build()
            chain.proceed(requestWithUserAgent)
        }
    }

    private fun getLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = BaseApp.HTTP_LOGGING_LEVEL
        }
    }
}
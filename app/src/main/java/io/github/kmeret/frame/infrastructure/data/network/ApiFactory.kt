package io.github.kmeret.frame.infrastructure.data.network

import io.github.kmeret.frame.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory<T> {

    fun create(apiInterface: Class<T>, baseUrl: String, headers: List<HeaderInterceptor> = emptyList()): T {
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

        val clientBuilder = OkHttpClient.Builder()
        headers.forEach { clientBuilder.addInterceptor(it) }

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return retrofitBuilder
                .client(clientBuilder.build())
                .build()
                .create(apiInterface)
    }

}
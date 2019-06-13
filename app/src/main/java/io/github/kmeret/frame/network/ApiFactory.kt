package io.github.kmeret.frame.network

import io.github.kmeret.frame.demo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory<T> {

    fun create(apiInterface: Class<T>, baseUrl: String, headers: List<HeaderInterceptor> = emptyList()): T {

        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

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
package io.github.kmeret.base.network

import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import io.github.kmeret.base.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory<T> {

    fun create(baseUrl: String, apiInterface: Class<T>): T {

        val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            Picasso.get().isLoggingEnabled = true
        }

        return retrofitBuilder
                .client(clientBuilder.build())
                .build()
                .create(apiInterface)

    }

}
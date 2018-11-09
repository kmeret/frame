package io.github.kmeret.frame.network

import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory<T> {

    fun create(baseUrl: String, apiInterface: Class<T>): T {

        val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))

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
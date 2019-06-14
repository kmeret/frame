package io.github.kmeret.frame.infrastructure.data.network

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ApiFactory(
    private val serverConfig: ServerConfig,
    private val httpClient: OkHttpClient
) {

    fun <T> create(apiInterface: Class<T>): T {
        val client = if (serverConfig.pin != null) {
            val sslPinning = CertificatePinner.Builder()
                .add(serverConfig.host, serverConfig.pin)
                .build()
            httpClient.newBuilder().certificatePinner(sslPinning).build()
        } else {
            httpClient
        }

        return Retrofit.Builder()
            .baseUrl(serverConfig.endpoint)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.networkGson))
            .build()
            .create(apiInterface)
    }

}
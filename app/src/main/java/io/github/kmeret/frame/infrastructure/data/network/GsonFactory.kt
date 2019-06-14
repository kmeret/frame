package io.github.kmeret.frame.infrastructure.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonFactory {
    val networkGson: Gson by lazy {
        GsonBuilder()
            .serializeNulls()
            .create()
    }
}
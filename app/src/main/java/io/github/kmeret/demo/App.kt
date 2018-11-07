package io.github.kmeret.demo

import android.app.Application
import io.github.kmeret.demo.di.*

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                //.contextModule(ContextModule(this))
                .networkModule(NetworkModule)
                //.storageModule(StorageModule)
                .build()
    }

}
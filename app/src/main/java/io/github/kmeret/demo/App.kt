package io.github.kmeret.demo

import android.app.Application
import io.github.kmeret.demo.di.AppComponent
import io.github.kmeret.demo.di.DaggerAppComponent
import io.github.kmeret.demo.di.NetworkModule

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .networkModule(NetworkModule)
                .build()
    }

}
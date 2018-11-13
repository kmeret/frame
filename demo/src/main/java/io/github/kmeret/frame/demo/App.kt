package io.github.kmeret.frame.demo

import android.app.Application
import io.github.kmeret.frame.demo.di.*
import org.koin.android.ext.android.startKoin

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() = startKoin(this, listOf(
            networkModule,
            storageModule,
            starsModule
    ))

}
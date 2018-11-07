package io.github.kmeret.demo

import android.app.Application
import io.github.kmeret.demo.network.NetworkModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        initStorage()
    }

    private fun initDI() = startKoin(this, listOf(
            NetworkModule
    ))

    private fun initStorage() {}

}
package io.github.kmeret.frame.application

import android.app.Application
import io.github.kmeret.frame.application.di.networkModule
import io.github.kmeret.frame.application.di.storageModule
import io.github.kmeret.frame.application.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() = startKoin(this, listOf(
            networkModule,
            storageModule,
            viewModelModule
    ))

}
package io.github.kmeret.frame.application

import io.github.kmeret.frame.application.di.networkModule
import io.github.kmeret.frame.application.di.storageModule
import io.github.kmeret.frame.application.di.viewModelModule
import io.github.kmeret.frame.infrastructure.application.BaseApp
import org.koin.android.ext.android.startKoin

class App : BaseApp() {

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
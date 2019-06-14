package io.github.kmeret.frame.application

import io.github.kmeret.frame.infrastructure.application.BaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : BaseApp() {
    override fun onInit() {
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(Modules.getModuleList())
        }
    }
}
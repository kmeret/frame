package io.github.kmeret.frame.application

import io.github.kmeret.frame.infrastructure.application.BaseApp
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : BaseApp() {

    override fun onInit() {
        initDI()
        initStorage()
    }

    private fun initDI() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(Modules.getList())
        }
    }

    private fun initStorage() {
        Realm.init(applicationContext)
    }
}
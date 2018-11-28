package io.github.kmeret.frame.demo

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import io.github.kmeret.frame.demo.di.*
import io.github.kmeret.frame.demo.navigation.ToastLiveData
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        ToastLiveData(this, ProcessLifecycleOwner.get().lifecycle)
    }

    private fun initDI() = startKoin(this, listOf(
            networkModule,
            storageModule,
            viewModelModule
    ))

}
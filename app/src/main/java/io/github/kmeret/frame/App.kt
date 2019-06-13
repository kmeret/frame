package io.github.kmeret.frame

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import io.github.kmeret.frame.di.networkModule
import io.github.kmeret.frame.di.storageModule
import io.github.kmeret.frame.di.viewModelModule
import io.github.kmeret.frame.navigation.ToastLiveData
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
package io.github.kmeret.frame.demo.navigation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ToastLiveData(private val application: Application, lifecycle: Lifecycle) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun sayHello() {
        Toast.makeText(application, "Hello", Toast.LENGTH_SHORT).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun sayBye() {
        Toast.makeText(application, "Bye", Toast.LENGTH_SHORT).show()
    }
}
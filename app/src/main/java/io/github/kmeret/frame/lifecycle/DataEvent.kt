package io.github.kmeret.frame.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class DataEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> {
            if (pending.compareAndSet(true, false)) observer.onChanged(it)
        })
    }

    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

}

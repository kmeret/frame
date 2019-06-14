package io.github.kmeret.frame.infrastructure.application.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.infrastructure.domain.coroutines.CompositeJob
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    private val compositeJob = CompositeJob()

    val commands = LiveCommand<VMCommand>()
    val isLoading = MutableLiveData<Boolean>()
    val errors = LiveCommand<Throwable>()

    override fun onCleared() {
        super.onCleared()
        compositeJob.clear()
    }

    abstract fun onBackPressed()

    abstract fun onInit()

    protected fun safeSubscribe(block: () -> Job) {
        compositeJob.add(block.invoke())
    }

}
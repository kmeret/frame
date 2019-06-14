package io.github.kmeret.frame.infrastructure.application.lifecycle

import android.os.Bundle
import androidx.lifecycle.LiveData
import io.github.kmeret.frame.infrastructure.presentation.layout.LayoutActivity

abstract class VMActivity<VM : BaseViewModel> : LayoutActivity(), VMHolder<VM> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.commands.subscribeCommand { onCommandReceived(it) }
        subscribeLiveData()
        viewModel.onInit()
    }

    abstract fun onCommandReceived(command: VMCommand)

    protected fun <T> LiveData<T?>.subscribe(action: (T) -> Unit) = observe(this@VMActivity, action)

    protected fun <T> LiveCommand<T>.subscribeCommand(action: (T) -> Unit) = observeCommand(this@VMActivity, action)
}

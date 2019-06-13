package io.github.kmeret.frame.infrastructure.application.lifecycle

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import io.github.kmeret.frame.infrastructure.presentation.layout.LayoutFragment

abstract class VMFragment<VM : BaseViewModel> : LayoutFragment(), VMHolder<VM> {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.commands.subscribeCommand { onCommandReceived(it) }
        viewModel.errors.subscribeCommand { layoutActivity?.showError(it) }
        initViewModel()
    }

    @CallSuper
    open fun onBackPressed() {
        viewModel.onClosed()
    }

    @CallSuper
    open fun onCommandReceived(command: VMCommand) {
        (activity as? VMActivity<*>)?.onCommandReceived(command)
    }

    protected fun <T> LiveData<T?>.subscribe(action: (T) -> Unit) = observe(viewLifecycleOwner, action)

    protected fun <T> LiveCommand<T>.subscribeCommand(action: (T) -> Unit) = observeCommand(viewLifecycleOwner, action)

}

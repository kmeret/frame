package io.github.kmeret.frame.infrastructure.presentation.base.viewmodel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.infrastructure.presentation.base.BaseFragment

abstract class ViewModelFragment<VM : ViewModel> : BaseFragment(),
    ViewModelHolder<VM> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    fun <T> LiveData<T>.observe(callback: (T) -> Unit) {
        this.observe(viewLifecycleOwner, callback)
    }

}

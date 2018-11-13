package io.github.kmeret.frame.lifecycle

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import io.github.kmeret.frame.android.base.BaseFragment

abstract class ViewModelFragment<VM : ViewModel> : BaseFragment(), ViewModelHolder<VM> {

    override lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

}

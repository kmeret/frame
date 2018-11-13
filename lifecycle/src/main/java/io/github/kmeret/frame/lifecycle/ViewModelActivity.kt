package io.github.kmeret.frame.lifecycle

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import io.github.kmeret.frame.android.base.BaseActivity

abstract class ViewModelActivity<VM : ViewModel> : BaseActivity(), ViewModelHolder<VM> {

    override lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        super.onCreate(savedInstanceState)
        initViewModel()
    }

}

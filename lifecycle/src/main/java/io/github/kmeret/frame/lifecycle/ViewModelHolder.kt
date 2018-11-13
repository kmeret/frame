package io.github.kmeret.frame.lifecycle

import androidx.lifecycle.ViewModel

interface ViewModelHolder<VM : ViewModel> {

    val viewModel: VM
    val viewModelClass: Class<VM>

    fun initViewModel()

}
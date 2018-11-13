package io.github.kmeret.frame.lifecycle

import androidx.lifecycle.ViewModel

interface ViewModelHolder<VM : ViewModel> {

    var viewModel: VM
    val viewModelClass: Class<VM>

    fun initViewModel()

}
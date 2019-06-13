package io.github.kmeret.frame.infrastructure.presentation.base.viewmodel

import androidx.lifecycle.ViewModel

interface ViewModelHolder<VM : ViewModel> {
    val viewModel: VM
    fun initViewModel()
}
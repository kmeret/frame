package io.github.kmeret.frame.infrastructure.application.lifecycle

interface VMHolder<VM : BaseViewModel> {
    val viewModel: VM
    fun initViewModel()
}
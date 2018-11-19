package io.github.kmeret.frame.demo.di

import io.github.kmeret.frame.demo.ui.stars.StarsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { StarsViewModel(get()) }
}
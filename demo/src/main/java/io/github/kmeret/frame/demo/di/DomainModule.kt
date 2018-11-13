package io.github.kmeret.frame.demo.di

import io.github.kmeret.frame.demo.domain.stars.GetStarredRepoUseCase
import io.github.kmeret.frame.demo.domain.stars.StarsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val starsModule = module {
    single { GetStarredRepoUseCase(get()) }
    viewModel { StarsViewModel(get()) }
}
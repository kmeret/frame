package io.github.kmeret.frame.application.di

import io.github.kmeret.frame.presentation.followers.FollowersViewModel
import io.github.kmeret.frame.presentation.following.FollowingViewModel
import io.github.kmeret.frame.presentation.profile.ProfileViewModel
import io.github.kmeret.frame.presentation.repos.ReposViewModel
import io.github.kmeret.frame.presentation.stars.StarsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { ProfileViewModel(androidApplication(), get(), get()) }
    viewModel { ReposViewModel(get()) }
    viewModel { StarsViewModel(get()) }
    viewModel { FollowersViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
}
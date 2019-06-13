package io.github.kmeret.frame.di

import io.github.kmeret.frame.github.GithubService
import io.github.kmeret.frame.network.ApiFactory
import org.koin.dsl.module.module

val networkModule = module {
    single {
        ApiFactory<GithubService>().create(
                GithubService::class.java,
                "https://api.github.com/"
        )
    }
}
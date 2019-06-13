package io.github.kmeret.frame.application.di

import io.github.kmeret.frame.data.github.GithubService
import io.github.kmeret.frame.infrastructure.data.network.ApiFactory
import org.koin.dsl.module.module

val networkModule = module {
    single {
        ApiFactory<GithubService>().create(
                GithubService::class.java,
                "https://api.github.com/"
        )
    }
}
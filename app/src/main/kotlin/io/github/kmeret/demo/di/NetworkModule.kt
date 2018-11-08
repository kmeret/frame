package io.github.kmeret.demo.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.github.kmeret.base.network.ApiFactory
import io.github.kmeret.demo.network.github.GithubService

@Module
object NetworkModule {

    @Provides
    @Reusable
    fun provideGithubService() = ApiFactory<GithubService>()
            .create("https://api.github.com/", GithubService::class.java)

}
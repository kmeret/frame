package io.github.kmeret.frame.demo.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.network.ApiFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    fun provideGithubService() = ApiFactory<GithubService>()
            .create("https://api.github.com/", GithubService::class.java)

}
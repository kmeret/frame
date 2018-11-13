package io.github.kmeret.frame.demo.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.github.kmeret.frame.demo.domain.stars.GetStarredRepoUseCase
import io.github.kmeret.frame.demo.github.GithubService

@Module
object DomainModule {

    @Provides
    @Reusable
    fun getStarredRepoUseCase(githubService: GithubService) = GetStarredRepoUseCase(githubService)

}
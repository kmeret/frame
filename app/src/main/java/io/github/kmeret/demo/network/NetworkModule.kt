package io.github.kmeret.demo.network

import io.github.kmeret.base.network.ApiFactory
import io.github.kmeret.demo.network.github.GithubInterface
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object NetworkModule : Module {
    override fun invoke() = module {
        single {
            ApiFactory<GithubInterface>()
                    .create("https://api.github.com/", GithubInterface::class.java)
        }
    }.invoke()
}
package io.github.kmeret.frame.demo.di

import dagger.Component
import io.github.kmeret.frame.demo.domain.stars.StarsViewModel
import io.github.kmeret.frame.demo.navigation.NavActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    NetworkModule::class,
    StorageModule::class,
    DomainModule::class
])
interface AppComponent {

    //todo add inject to properties here

    fun inject(starsViewModel: StarsViewModel)

}
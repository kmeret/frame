package io.github.kmeret.frame.demo.di

import dagger.Component
import io.github.kmeret.frame.demo.navigation.NavActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    NetworkModule::class,
    StorageModule::class
])
interface AppComponent {

    fun inject(navActivity: NavActivity)

}
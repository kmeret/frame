package io.github.kmeret.demo.di

import dagger.Component
import io.github.kmeret.demo.navigation.NavActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class
])
interface AppComponent {

    fun inject(navActivity: NavActivity)

}
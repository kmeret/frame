package io.github.kmeret.frame.demo.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ContextModule(private val application: Application) {

    @Reusable
    @Provides
    fun provideApplicationContext(): Context = application

}
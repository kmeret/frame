package io.github.kmeret.demo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.github.kmeret.demo.BuildConfig
import io.github.kmeret.demo.storage.Database

@Module
object StorageModule {

    @Reusable
    @Provides
    fun provideDatabase(application: Application): Database =
            Room.databaseBuilder(
                    application,
                    Database::class.java,
                    BuildConfig.APPLICATION_ID
            ).build()

    @Reusable
    @Provides
    fun provideRepoDao(database: Database) = database.repoDao()

    @Reusable
    @Provides
    fun provideUserDao(database: Database) = database.userDao()

}
package io.github.kmeret.frame.application

import io.github.kmeret.frame.data.network.login.GithubAuthApi
import io.github.kmeret.frame.data.network.open.GithubOpenApi
import io.github.kmeret.frame.data.network.open.GithubOpenApiFactory
import io.github.kmeret.frame.data.network.user.GithubUserApi
import io.github.kmeret.frame.data.network.user.GithubUserApiFactory
import io.github.kmeret.frame.data.repos.AuthDataRepo
import io.github.kmeret.frame.data.repos.UserDataRepo
import io.github.kmeret.frame.domain.cases.AuthInteractor
import io.github.kmeret.frame.domain.cases.UserInteractor
import io.github.kmeret.frame.domain.repos.AuthRepo
import io.github.kmeret.frame.domain.repos.UserRepo
import io.github.kmeret.frame.infrastructure.application.permission.PermissionProvider
import io.github.kmeret.frame.infrastructure.data.storage.RealmDao
import io.github.kmeret.frame.presentation.main.MainViewModel
import io.github.kmeret.frame.presentation.screens.auth.LoginViewModel
import io.github.kmeret.frame.presentation.screens.followers.FollowersViewModel
import io.github.kmeret.frame.presentation.screens.following.FollowingViewModel
import io.github.kmeret.frame.presentation.screens.profile.ProfileViewModel
import io.github.kmeret.frame.presentation.screens.repos.ReposViewModel
import io.github.kmeret.frame.presentation.screens.stars.StarsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

object Modules {
    fun getList() = listOf(
        appModule,
        networkModule,
        dataModule,
        storageModule,
        navigationModule,
        domainModule,
        viewModelModule
    )

    private val appModule = module {
        single { PermissionProvider(androidApplication()) }
    }

    private val networkModule = module {
        single { GithubOpenApiFactory() }
        single { get<GithubOpenApiFactory>().create(GithubOpenApi::class.java) }
        single { get<GithubOpenApiFactory>().create(GithubAuthApi::class.java) }

        single { GithubUserApiFactory(get()) }
        single { get<GithubUserApiFactory>().create(GithubUserApi::class.java) }
    }

    private val dataModule = module {
        single<AuthRepo> { AuthDataRepo(get(), get()) }
        single<UserRepo> { UserDataRepo(get()) }
    }

    private val storageModule = module {
        single { RealmDao() }
    }

    private val navigationModule = module {
        single { Cicerone.create() }
        single { get<Cicerone<Router>>().router }
        single { get<Cicerone<Router>>().navigatorHolder }
    }

    private val domainModule = module {
        single { AuthInteractor(get()) }
        single { UserInteractor(get()) }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get(), get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { ProfileViewModel(get()) }
        viewModel { ReposViewModel(get()) }
        viewModel { StarsViewModel(get()) }
        viewModel { FollowersViewModel(get()) }
        viewModel { FollowingViewModel(get()) }
    }
}

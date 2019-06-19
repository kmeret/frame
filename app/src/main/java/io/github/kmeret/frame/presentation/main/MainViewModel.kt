package io.github.kmeret.frame.presentation.main

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.R
import io.github.kmeret.frame.application.Screens
import io.github.kmeret.frame.domain.cases.AuthInteractor
import io.github.kmeret.frame.domain.model.auth.AuthCommand
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.VMCommand
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext
import ru.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router,
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    val isUserSignIn = MutableLiveData<Boolean>()

    override fun onInit() = startApp()

    override fun onBackPressed() = router.finishChain()

    fun onCommandReceived(command: VMCommand) {
        when(command) {
            is AuthCommand.SignIn -> signIn()
            is AuthCommand.SignOut -> signOut()
        }
    }

    fun onBottomNavBarPressed(itemId: Int) {
        val rootScreen = when (itemId) {
            R.id.nav_profile -> Screens.ProfileScreen()
            R.id.nav_repos -> Screens.ReposScreen()
            R.id.nav_stars -> Screens.StarsScreen()
            R.id.nav_followers -> Screens.FollowersScreen()
            R.id.nav_following -> Screens.FollowingScreen()
            else -> return
        }
        router.newRootScreen(rootScreen)
    }

    private fun startApp() {
        when(authInteractor.getAuthData()) {
            null -> signOut()
            else -> signIn()
        }
    }

    private fun signIn() {
        isUserSignIn.onNext(true)
        router.newRootScreen(Screens.ProfileScreen())
    }

    private fun signOut() {
        authInteractor.signOut()
        isUserSignIn.onNext(false)
        router.newRootScreen(Screens.LoginScreen())
    }
}